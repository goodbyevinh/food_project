package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.CategoryDTO;
import com.cybersoft.food_project.entity.CategoryEntity;
import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.repository.CategoryRepository;
import com.cybersoft.food_project.utils.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getExplorerCategoryLimit6() {
        List<CategoryEntity> categories = categoryRepository.getExplorerCategoryLimit6();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categories.forEach( category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setTitle(category.getName());
            categoryDTO.setImage(Url.Root.getPath() + category.getImage());
            int option = category.getFoods().size();
            categoryDTO.setOption(option);
            categoryDTOS.add(categoryDTO);
        });
        return categoryDTOS;
    }

    @Override
    public List<CategoryDTO> getExplorerCategory() {
        List<CategoryEntity> categories = categoryRepository.getExplorerCategory();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categories.forEach( category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setTitle(category.getName());
            categoryDTO.setImage(Url.Root.getPath() + category.getImage());
            int option = category.getFoods().size();
            categoryDTO.setOption(option);
            categoryDTOS.add(categoryDTO);
        });
        return categoryDTOS;
    }

    @Override
    public List<String> getAllCategoryNameByRestaurant(int id) {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List <String> list = categoryEntities.stream()
                .filter(category -> category.getFoods().stream().
                        anyMatch(food -> food.getRestaurant().getId() == id))
                .map(category -> category.getName())
                .collect(Collectors.toList());
        return list;
    }
}
