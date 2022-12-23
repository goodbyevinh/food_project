package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.CategoryDTO;
import com.cybersoft.food_project.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getExplorerCategoryLimit6();
    List<CategoryDTO> getExplorerCategory();
    List<String> getAllCategoryNameByRestaurant(int id);

}
