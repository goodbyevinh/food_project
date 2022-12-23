package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.RestaurantDTO;
import com.cybersoft.food_project.dto.RestaurantDetailDTO;
import com.cybersoft.food_project.dto.RestaurantFavouriteDTO;
import com.cybersoft.food_project.entity.FoodEntity;
import com.cybersoft.food_project.entity.RestaurantEntity;
import com.cybersoft.food_project.entity.RestaurantReviewEntity;
import com.cybersoft.food_project.model.FoodModel;
import com.cybersoft.food_project.repository.RestaurantRepository;
import com.cybersoft.food_project.utils.Url;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public List<RestaurantDTO> getRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        // xử lý dat = [{title, image, avgRate}]
        for (RestaurantEntity data : restaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setTitle(data.getName());
            restaurantDTO.setImage(Url.Root.getPath() +data.getImage());
            float sumRate = 0;
            float avgRate = 0;
            for (RestaurantReviewEntity dataReview: data.getRestaurantReviews()) {
                sumRate += dataReview.getRate();
            }
            if (data.getRestaurantReviews().size() > 0) {
                avgRate = sumRate / data.getRestaurantReviews().size();
            }
            restaurantDTO.setAvgRate(avgRate);
            restaurantDTO.setReview(data.getRestaurantReviews().size());
            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    @Override
    public List<RestaurantDTO> getRestaurantsLimit() {
        List<RestaurantEntity> restaurants = restaurantRepository.findRestaurantsLimit6();
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        // xử lý dat = [{title, image, avgRate}]
        for (RestaurantEntity data : restaurants) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setTitle(data.getName());
            restaurantDTO.setImage(Url.Root.getPath() +data.getImage());
            float sumRate = 0;
            float avgRate = 0;
            for (RestaurantReviewEntity dataReview: data.getRestaurantReviews()) {
                sumRate += dataReview.getRate();
            }
            if (data.getRestaurantReviews().size() > 0) {
                avgRate = sumRate / data.getRestaurantReviews().size();
            }
            restaurantDTO.setAvgRate(avgRate);
            restaurantDTO.setReview(data.getRestaurantReviews().size());
            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    @Override
    public List<RestaurantDTO> getRestaurantsByName(String name) {
        List<RestaurantEntity> restaurants = restaurantRepository.findRestaurantEntitiesByNameContains(name);
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        restaurants.forEach( restaurant -> {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setTitle(restaurant.getName());
            restaurantDTO.setImage(Url.Root.getPath() + restaurant.getImage());
            float sum = 0;
            float avg = 0;
            for (RestaurantReviewEntity data : restaurant.getRestaurantReviews()) {
                sum = sum + data.getRate();
            };
            if (restaurant.getRestaurantReviews().size() == 0) {
                avg = 0;
            } else {
                avg = sum / restaurant.getRestaurantReviews().size();
            }
            restaurantDTO.setAvgRate(avg);
            restaurantDTOS.add(restaurantDTO);
        });
        return restaurantDTOS;
    }

    @Override
    public List<RestaurantFavouriteDTO> getFavouriteRestaurants(String email) {
        List<RestaurantEntity> restaurants = restaurantRepository.findRestaurantBookmarkByUser(email);
        List<RestaurantFavouriteDTO> restaurantFavouriteDTOS = new ArrayList<>();
        restaurants.forEach( restaurant ->{
            RestaurantFavouriteDTO restaurantFavouriteDTO = new RestaurantFavouriteDTO();
            restaurantFavouriteDTO.setId(restaurant.getId());
            restaurantFavouriteDTO.setTitle(restaurant.getName());
            restaurantFavouriteDTO.setImage(Url.Root.getPath() + restaurant.getImage());
            restaurantFavouriteDTO.setReview(restaurant.getRestaurantReviews().size());
            restaurantFavouriteDTO.setAvgRate((float) restaurant.getRestaurantReviews()
                    .stream()
                    .mapToDouble(value -> value.getRate())
                    .average()
                    .orElse(0)
            );
            restaurantFavouriteDTOS.add(restaurantFavouriteDTO);
        });
        return restaurantFavouriteDTOS;
    }

    @Override
    //@Cacheable("detail_restaurant")
    public RestaurantDetailDTO getRestaurantDetail(int id) {
        // Kiểm tra key redis có tồn tại không ? nếu có lấy data từ redis, nếu không thì query va set data redis
        String key = "res" + id;
        Gson gson = new Gson();
        RestaurantDetailDTO restaurantDetailDTO = new RestaurantDetailDTO();
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            String data = (String) redisTemplate.opsForValue().get(key);
            restaurantDetailDTO = gson.fromJson(data, RestaurantDetailDTO.class );
            return  restaurantDetailDTO;
        }

        // Optional: Có hoặc không có cũng được( dữ liệu có thể bị null)
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(id);

        if (restaurant.isPresent()) {
            // có thể xử lý
            restaurantDetailDTO.setTitle(restaurant.get().getName());
            restaurantDetailDTO.setImage(Url.Root.getPath() + restaurant.get().getImage());
            float avgRate = 0;
            float sumRate = 0;
            for(RestaurantReviewEntity data : restaurant.get().getRestaurantReviews()) {
                sumRate += data.getRate();
            }
            if (restaurant.get().getRestaurantReviews().size() > 0) {
                avgRate = sumRate / restaurant.get().getRestaurantReviews().size();
            }
            restaurantDetailDTO.setAvgRate(avgRate);
            restaurantDetailDTO.setReview(restaurant.get().getRestaurantReviews().size());
            restaurantDetailDTO.setDesc(restaurant.get().getDescription());
            restaurantDetailDTO.setCategory(restaurant.get().getCategory());
            List<FoodModel> foodModels = new ArrayList<>();
            for (FoodEntity food : restaurant.get().getFoods()) {
                FoodModel foodModel = new FoodModel();
                foodModel.setId(food.getId());
                foodModel.setImage(food.getImage());
                foodModel.setPrice(food.getPrice());
                foodModel.setName(food.getName());
                foodModels.add(foodModel);
            }
            restaurantDetailDTO.setFoods(foodModels);
        }


        String json = gson.toJson(restaurantDetailDTO);
        redisTemplate.opsForValue()
                .set(key, json );

        return  restaurantDetailDTO;
    }
    @Override
    @CacheEvict(value = "detail_restaurant" , allEntries = true)
    public void clearAllCache() {

    }
}
