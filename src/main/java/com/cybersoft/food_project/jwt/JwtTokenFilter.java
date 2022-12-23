package com.cybersoft.food_project.jwt;

import com.cybersoft.food_project.entity.UserEntity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private Gson gson = new Gson();
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Cắt header và lấy token
        String token = getTokenFromHeader(request);
        if (token != null) {
            // kiểm tra token có phải do hệ thống sinh ra
            if (jwtTokenHelper.validateToken(token)) {
                // token hợp lệ
                String json = jwtTokenHelper.decodeToken(token);
                Map<String, Object> map = gson.fromJson(json, Map.class);
                System.out.println(map);
                if (StringUtils.hasText(map.get("type").toString()) && !map.get("type").toString().equals("refresh")) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(map.get("username"),"", new ArrayList<>());
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        // Lấy giá trị token ở Header có key là Authorization
        String strToken = request.getHeader("Authorization");
        if (StringUtils.hasText(strToken) && strToken.startsWith("Bearer")) {
            // Xử lý khi token hợp lệ
            String finalToken = strToken.substring(7);
            return finalToken;
        } else  {
            return null;
        }
    }
}
