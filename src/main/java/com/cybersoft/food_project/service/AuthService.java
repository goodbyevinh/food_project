package com.cybersoft.food_project.service;

import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.payload.request.SignUpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AuthService {

    boolean checkLogin(String email, String password);
    UserEntity checkLogin(String email);

    boolean insertUser(SignUpRequest request);
    boolean insertUserDetail(String email);
}
