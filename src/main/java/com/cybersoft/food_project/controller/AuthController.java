package com.cybersoft.food_project.controller;


import com.cybersoft.food_project.entity.UserDetailEntity;
import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.exception.DevineZeroException;
import com.cybersoft.food_project.jwt.JwtTokenHelper;
import com.cybersoft.food_project.payload.request.SignInRequest;
import com.cybersoft.food_project.payload.request.SignUpRequest;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.payload.response.DataTokenResponse;
import com.cybersoft.food_project.service.AuthService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.time.Duration;

@RestController
//@CrossOrigin // cho phép những domain khác với domain của api truy cập vào
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenHelper jwtTokenHelper;
//    @PostMapping("")
//    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
//        boolean isSuccess = authService.checkLogin(signInRequest.getUsername(), signInRequest.getPassword());
//        DataResponse dataResponse = new DataResponse();
//        dataResponse.setStatus(HttpStatus.OK.value());
//        dataResponse.setDesc("");
//        dataResponse.setData("");
//        dataResponse.setSuccess(isSuccess);
//
//        return new ResponseEntity<>(dataResponse , HttpStatus.OK);
//    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        logger.info(gson.toJson(request));

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        long expiredDate = 8 * 60 * 60 * 1000;
        long refreshExpiredDate = 8 * 60 * 60 * 1000;

        //Khi nhập thành công trả thêm refesh token (không có thời gian expired)
        //Tạo controller /refresh-token
        //Kiểm tra refesh token có hợp hay không
        // Nếu hợp lệ trả ra token mới

        String token = jwtTokenHelper.generateToken(request.getUsername(),"authen", expiredDate);
        String refreshToken = jwtTokenHelper.generateToken(request.getUsername(),"refresh", refreshExpiredDate);

        DataTokenResponse dataTokenResponse = new DataTokenResponse();
        dataTokenResponse.setToken(token);
        dataTokenResponse.setFreshToken(refreshToken);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("");
        dataResponse.setData(dataTokenResponse);
        dataResponse.setSuccess(true);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", dataTokenResponse.getFreshToken()) // key & value
                .httpOnly(true)
                .secure(true)
                .path("/api/refresh-token")
                .maxAge(Duration.ofHours(1))
                .sameSite("None")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return new ResponseEntity<>(dataResponse , HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request, HttpServletResponse response) {
        boolean isSuccess = authService.insertUser(request) && authService.insertUserDetail(request.getEmail());
        DataResponse dataResponse = new DataResponse();
        if (isSuccess) {
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication auth = authenticationManager.authenticate(authRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            long expiredDate = 8 * 60 * 60 * 1000;
            long refreshExpiredDate = 8 * 60 * 60 * 1000;


            String token = jwtTokenHelper.generateToken(request.getEmail(),"authen", expiredDate);
            String refreshToken = jwtTokenHelper.generateToken(request.getEmail(),"refresh", refreshExpiredDate);

            DataTokenResponse dataTokenResponse = new DataTokenResponse();
            dataTokenResponse.setToken(token);
            dataTokenResponse.setFreshToken(refreshToken);

            dataResponse.setDesc("SignUp sucess");
            dataResponse.setStatus(HttpStatus.OK.value());
            dataResponse.setSuccess(isSuccess);
            dataResponse.setData(dataTokenResponse);

            ResponseCookie cookie = ResponseCookie.from("refreshToken", dataTokenResponse.getFreshToken()) // key & value
                    .httpOnly(true)
                    .secure(true)
                    .path("/api/refresh-token")
                    .maxAge(Duration.ofHours(1))
                    .sameSite("None")
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }  else {
            dataResponse.setDesc("SignUp fail");
            dataResponse.setStatus(HttpStatus.OK.value());
            dataResponse.setSuccess(isSuccess);
            dataResponse.setData("");
        }
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    //Khai báo và khởi tạo logger
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @GetMapping("/test")
    public String test() {
        logger.trace("This is logger trace");
        logger.debug("This is logger debug");
        logger.info("This is logger info");
        logger.warn("This is logger warn");
        logger.error("This is logger error");
        try {
            int x = 2/0;
        } catch (Exception e) {
            throw new DevineZeroException(e.getMessage());
        }
        return "hello";
    }
    @GetMapping("/cookie")
    public ResponseEntity<?> testCookie(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("platform","mobile");

        // expires in 7 days
        cookie.setMaxAge(1 * 60 * 60);

        // optional properties
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        // add cookie to response
        response.addCookie(cookie);
        DataResponse dataResponse = new DataResponse();
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
