package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.dto.CategoryDTO;
import com.cybersoft.food_project.entity.CategoryEntity;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryNameByRestaurant(@PathVariable("id") int id){
        List<String> categories = categoryService.getAllCategoryNameByRestaurant(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(categories);
        dataResponse.setDesc("");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/explore")
    public ResponseEntity<?> getExplorerCategory() {
        List<CategoryDTO> categories = categoryService.getExplorerCategory();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(categories);
        dataResponse.setDesc("");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/explorelimit")
    public ResponseEntity<?> getExplorerCategoryLimit() {
        List<CategoryDTO> categories = categoryService.getExplorerCategoryLimit6();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(categories);
        dataResponse.setDesc("");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
