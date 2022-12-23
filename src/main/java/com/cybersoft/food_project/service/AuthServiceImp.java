package com.cybersoft.food_project.service;

import com.cybersoft.food_project.entity.UserDetailEntity;
import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.payload.request.SignUpRequest;
import com.cybersoft.food_project.repository.UserDetailReposiotry;
import com.cybersoft.food_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailReposiotry userDetailReposiotry;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public boolean checkLogin(String email, String password) {
        List<UserEntity> users = userRepository.findByEmailAndPassword(email, password);
        return users.size() > 0;
    }

    @Override
    public UserEntity checkLogin(String email) {
        List<UserEntity> users = userRepository.findByEmail(email);
        return users.size() > 0 ? users.get(0) : null;
    }
    @Override
    public boolean insertUser(SignUpRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullname());
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean insertUserDetail(String email) {
        UserEntity user = userRepository.findUserEntityByEmail(email);
        UserDetailEntity userDetail = new UserDetailEntity();
        userDetail.setIdUser(user.getId());
        user.setUserDetail(userDetail);
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
