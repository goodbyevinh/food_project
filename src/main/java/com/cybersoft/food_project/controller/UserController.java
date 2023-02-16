package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.dto.UserDetailDTO;
import com.cybersoft.food_project.entity.UserDetailEntity;
import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/update")
    ResponseEntity<?> updateProfile(@RequestBody UserDetailDTO profile) {
        DataResponse dataResponse = new DataResponse();
        boolean isSuccess = userService.updateUser(profile);
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(isSuccess ? "update success" : "update fail");
        dataResponse.setData("");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/detail")
    ResponseEntity<?> getProfile() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        UserDetailDTO user = userService.getUserDetailByEmail(username);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("");
        dataResponse.setData(user);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/updateaddress")
    ResponseEntity<?> updateAddress(@RequestParam("address")String address) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        boolean isSuccess = userService.updateAddress(username, address);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(isSuccess ? "update success" : "update fail");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/updateaddresswork")
    ResponseEntity<?> updateAddressWork(@RequestParam("address")String address) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        boolean isSuccess = userService.updateAddressWork(username, address);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(isSuccess ? "update success" : "update fail");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/bookmarkfood/insert")
    ResponseEntity<?> insertBookmarkFood(@RequestParam("id") int id){
        boolean isSuccess = userService.insertBookmarkFood(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(isSuccess ? "insert bookmark success" : "insert bookmark fail");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/bookmarkfood/delete")
    ResponseEntity<?> deleteBookmarkFood(@RequestParam("id") int id){
        boolean isSuccess = userService.deletebookmarkFood(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(isSuccess ? "delete bookmark success" : "delete bookmark fail");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/bookmarkrestaurant/insert")
    ResponseEntity<?> insertBookmarkRestaurant(@RequestParam("id") int id){
        boolean isSuccess = userService.insertBookmarkRestaurant(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(isSuccess ? "insert bookmark success" : "insert bookmark fail");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/bookmarkrestaurant/delete")
    ResponseEntity<?> deleteBookmarkRestaurant(@RequestParam("id") int id){
        boolean isSuccess = userService.deletebookmarkRestaurant(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(isSuccess ? "delete bookmark success" : "delete bookmark fail");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/favourite/restaurant/{id}")
    ResponseEntity <?> checkFavouriteRestaurant(@PathVariable("id") int id) {
        System.out.println(id);
        boolean isSuccess = userService.checkBookmarkRestaurant(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("check favourite restaurant");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    String test(@PathVariable("id") int id) {
        return "test";
    }
    @GetMapping("/favourite/food/{id}")
    ResponseEntity <?> checkFavouriteFood(@PathVariable("id") int id) {
        boolean isSuccess = userService.checkBookmarkFood(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(isSuccess);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("check favourite food");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
