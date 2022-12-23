package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.UserDetailDTO;
import com.cybersoft.food_project.entity.BookmarkFoodEntity;
import com.cybersoft.food_project.entity.UserEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UserService {
    UserDetailDTO getUserDetailByEmail(String email);
    boolean updateUser(UserDetailDTO userDetailDTO);
    boolean updateAddress(String email, String address);
    boolean updateAddressWork(String email, String address);
    boolean insertBookmarkFood(int id);
    boolean deletebookmarkFood(int id);
    boolean insertBookmarkRestaurant(int id);
    boolean deletebookmarkRestaurant(int id);
    boolean checkBookmarkRestaurant(int id);
    boolean checkBookmarkFood(int id);
}
