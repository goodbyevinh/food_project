package com.cybersoft.food_project.controller;


import com.cybersoft.food_project.payload.request.SignInRequest;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin // cho phép những domain khác với domain của api truy cập vào
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        boolean isSuccess = loginService.checkLogin(signInRequest.getUsername(), signInRequest.getPassword());
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("");
        dataResponse.setData("");
        dataResponse.setSuccess(isSuccess);

        return new ResponseEntity<>(dataResponse , HttpStatus.OK);
    }
}
