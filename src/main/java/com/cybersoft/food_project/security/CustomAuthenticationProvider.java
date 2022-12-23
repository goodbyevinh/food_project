package com.cybersoft.food_project.security;

import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AuthService authService;
    //Lỗi vòng lặp do 2 class cùng chạy nên thêm @ Config after
//    @Autowired
//    //@Qualifier("name") chỉ định tên bean mà mình muốn lấy trên container
//    PasswordEncoder passwordEncoder;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Xử lý logic code đăng nhập thành công hay thất bại

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity user = authService.checkLogin(username);

        if (user != null ) {
            boolean isMatchPassword = passwordEncoder.matches(password, user.getPassword());
            if (isMatchPassword) {
                return new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword(), new ArrayList<>());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
