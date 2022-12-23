package com.cybersoft.food_project.service;


import com.cybersoft.food_project.dto.FoodDTO;
import com.cybersoft.food_project.dto.FoodDetailDTO;
import com.cybersoft.food_project.dto.FoodFavouriteDTO;
import com.cybersoft.food_project.entity.BookmarkFoodEntity;
import com.cybersoft.food_project.entity.FoodAddonEntity;
import com.cybersoft.food_project.entity.FoodEntity;
import com.cybersoft.food_project.model.FoodAddonModel;
import com.cybersoft.food_project.repository.FoodAddonRepository;
import com.cybersoft.food_project.repository.FoodRepository;
import com.cybersoft.food_project.utils.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FoodServiceImp implements FoodService{

    @Autowired
    FoodRepository foodRepository;


    @Override
    public List<FoodDTO> getFoodsByCategoryLimit6(int categoryId) {
        List<FoodEntity> foods = foodRepository.findFoodEntitiesByCategoryLimit6(categoryId);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        foods.forEach(food -> {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setName(food.getName());
            foodDTO.setImage(Url.Root.getPath() + food.getImage());
            foodDTOS.add(foodDTO);
        });
        return foodDTOS;
    }

    @Override
    public List<FoodDTO> getFoodsByCategory(int categoryId) {
        List<FoodEntity> foods = foodRepository.findFoodEntitiesByCategory_Id(categoryId);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        foods.forEach(food -> {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setName(food.getName());
            foodDTO.setImage(Url.Root.getPath() + food.getImage());
            OptionalDouble avg = food.getFoodReviews().stream()
                                    .mapToDouble( value -> value.getRate())
                                    .average();
            float avgRate = (float) avg.orElse(0);
            foodDTO.setAvgRate(avgRate);
            foodDTOS.add(foodDTO);
        });
        return foodDTOS;
    }

    @Override
    public List<FoodDTO> getFoodsByRestaurant(int id) {
        List<FoodEntity> foods = foodRepository.findFoodEntitiesByRestaurant_Id(id);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        foods.forEach(food -> {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setName(food.getName());
            foodDTO.setImage(Url.Root.getPath() + food.getImage());
            foodDTO.setId(food.getId());
            foodDTO.setReview(food.getFoodReviews().size());
            foodDTO.setCategory(food.getCategory().getName());
            OptionalDouble avg = food.getFoodReviews().stream()
                    .mapToDouble( value -> value.getRate())
                    .average();
            float avgRate = (float) avg.orElse(0);
            foodDTO.setAvgRate(avgRate);
            foodDTOS.add(foodDTO);
        });
        return foodDTOS;
    }

    @Override
    public List<FoodFavouriteDTO> getFoodsFavouritesByUser(String email) {
        List<FoodEntity> foods = foodRepository.findFoodBookmarkByUser(email);
        List<FoodFavouriteDTO> foodFavouriteDTOS = new ArrayList<>();
        foods.forEach(food -> {
            FoodFavouriteDTO foodFavouriteDTO = new FoodFavouriteDTO();
            foodFavouriteDTO.setTitle(food.getName());
            foodFavouriteDTO.setReview(food.getFoodReviews().size());
            foodFavouriteDTO.setCategory(food.getCategory().getName());
            foodFavouriteDTO.setImage(Url.Root.getPath() + food.getImage());
            foodFavouriteDTO.setAvgRate((float) food.getFoodReviews()
                    .stream()
                    .mapToDouble(value -> value.getRate())
                    .average()
                    .orElse(0)
            );
            foodFavouriteDTOS.add(foodFavouriteDTO);
        });
        return foodFavouriteDTOS;
    }

    @Override
    public FoodDetailDTO getFoodDetailById(int id) {
        Optional<FoodEntity> food = foodRepository.findById(id);
        if (!food.isPresent() || food.get().getFoodDetail() == null || food.get().getFoodAddons() == null) {
            return null;
        }
        FoodDetailDTO foodDetailDTO = new FoodDetailDTO();
        foodDetailDTO.setTitle(food.get().getName());
        foodDetailDTO.setDescription(food.get().getFoodDetail().getDescription());
        foodDetailDTO.setImage(Url.Root.getPath() + food.get().getImage());

        List<FoodAddonModel> foodAddons = new ArrayList<>();

        food.get().getFoodAddons().forEach( foodAddon -> {
            FoodAddonModel foodAddonModel = new FoodAddonModel();
            foodAddonModel.setId(foodAddon.getId());
            foodAddonModel.setName(foodAddon.getName());
            foodAddons.add(foodAddonModel);
        });
        foodDetailDTO.setPrice(food.get().getPrice());
        foodDetailDTO.setFoodAddons(foodAddons);

        return foodDetailDTO;
    }

    @Override
    public List<FoodDTO> getFoodsByName(String name) {
        List<FoodEntity> foods = foodRepository.findFoodEntitiesByNameContains(name);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        foods.forEach( food -> {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setName(food.getName());
            foodDTO.setImage(Url.Root.getPath() + food.getImage());
            foodDTOS.add(foodDTO);
        });
        return foodDTOS;
    }

    @Override
    public Map<Integer,Map<String, Integer>> getCart(Set<Integer> foods) {
        List<FoodEntity> foodEntities = foodRepository.findAllById(foods);
        Map<Integer,Map<String, Integer>> map = new HashMap<>();
        foodEntities.forEach( foodEntity -> {
            Map<String, Integer> subMap = new HashMap<>();
            subMap.put(Url.Root.getPath() + foodEntity.getImage(), foodEntity.getPrice());
            map.put(foodEntity.getId(), subMap);
        });
        return map;
    }

}
