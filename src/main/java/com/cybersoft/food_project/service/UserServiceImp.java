package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.UserDetailDTO;
import com.cybersoft.food_project.entity.BookmarkFoodEntity;
import com.cybersoft.food_project.entity.BookmarkRestaurantEntity;
import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.repository.UserRepository;
import com.cybersoft.food_project.utils.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetailDTO getUserDetailByEmail(String email) {
        UserEntity user = userRepository.findUserEntityByEmail(email);
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        if (user.getUserDetail() == null) {
            userDetailDTO.setAvatar("");
            userDetailDTO.setPhone("");
            userDetailDTO.setFullname(user.getFullName());
            userDetailDTO.setEmail(user.getEmail());
        } else {
            userDetailDTO.setAvatar(Url.Root.getPath() + user.getUserDetail().getAvatar());
            userDetailDTO.setPhone(user.getUserDetail().getMobilePhone());
            userDetailDTO.setFullname(user.getFullName());
            userDetailDTO.setEmail(user.getEmail());
        }
        return userDetailDTO;
    }

    @Override
    public boolean updateUser(UserDetailDTO userDetailDTO) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        UserEntity user = userRepository.findUserEntityByEmail(username);
        user.setEmail(userDetailDTO.getEmail());
        user.setFullName(userDetailDTO.getFullname());
        user.getUserDetail().setMobilePhone(userDetailDTO.getPhone());
        user.getUserDetail().setAvatar(userDetailDTO.getAvatar());
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddress(String email, String address) {
        UserEntity user = userRepository.findUserEntityByEmail(email);
        user.getUserDetail().setAddress(address);
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddressWork(String email, String address) {
        try {
            UserEntity user = userRepository.findUserEntityByEmail(email);
            user.getUserDetail().setAddressWork(address);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertBookmarkFood(int id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        try {
            UserEntity user = userRepository.findUserEntityByEmail(username);
            BookmarkFoodEntity bookmarkFood = new BookmarkFoodEntity();
            bookmarkFood.setIdUser(user.getId());
            bookmarkFood.setIdFood(id);
            if (user.getBookmarkFoods() == null ) {
                Set<BookmarkFoodEntity> set = new HashSet<>();
                set.add(bookmarkFood);
                user.setBookmarkFoods(set);
            } else {
                user.getBookmarkFoods().add(bookmarkFood);
            }
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletebookmarkFood(int id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        try {
            UserEntity user = userRepository.findUserEntityByEmail(username);
            boolean isDelete = user.getBookmarkFoods()
                    .removeIf(bookmarkFoodEntity -> bookmarkFoodEntity.getIdUser() == user.getId()
                                                    && bookmarkFoodEntity.getIdFood() == id);
            userRepository.save(user);
            return isDelete;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertBookmarkRestaurant(int id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        try {
            UserEntity user = userRepository.findUserEntityByEmail(username);
            BookmarkRestaurantEntity bookmarkRestaurant = new BookmarkRestaurantEntity();
            bookmarkRestaurant.setIdUser(user.getId());
            bookmarkRestaurant.setIdRestaurant(id);
            if (user.getBookmarkRestaurants() == null ) {
                Set<BookmarkRestaurantEntity> set = new HashSet<>();
                set.add(bookmarkRestaurant);
                user.setBookmarkRestaurants(set);
            } else {
                user.getBookmarkRestaurants().add(bookmarkRestaurant);
            }
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletebookmarkRestaurant(int id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        try {
            UserEntity user = userRepository.findUserEntityByEmail(username);
            boolean isDelete = user.getBookmarkRestaurants()
                    .removeIf(bookmarkRestaurant -> bookmarkRestaurant.getIdUser() == user.getId()
                            && bookmarkRestaurant.getIdRestaurant() == id);
            userRepository.save(user);
            return isDelete;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkBookmarkRestaurant(int id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        UserEntity user = userRepository.findUserEntityByEmail(username);
        return user.getBookmarkRestaurants().stream().anyMatch(bookmarkRestaurant -> bookmarkRestaurant.getIdRestaurant() == id
                                                                && bookmarkRestaurant.getIdUser() == user.getId());
    }

    @Override
    public boolean checkBookmarkFood(int id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        UserEntity user = userRepository.findUserEntityByEmail(username);
        return user.getBookmarkFoods().stream().anyMatch(bookmarkFood -> bookmarkFood.getIdFood() == id
                && bookmarkFood.getIdUser() == user.getId());
    }
}
