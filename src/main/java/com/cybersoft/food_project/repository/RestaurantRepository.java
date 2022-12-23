package com.cybersoft.food_project.repository;

import com.cybersoft.food_project.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

//    // tính avg phải join bảng rv
//    @Query("select r from restaurant r join restaurant_review rv on r.id = rv.restaurant.id group by r.id having avg(rv.rate) > 4.5")
//    List<RestaurantEntity>findFavouriteRestaurantEntity() ;

    @Query(value = "select r.* from restaurant r order by r.id asc limit 6", nativeQuery = true)
    List<RestaurantEntity> findRestaurantsLimit6();
    List<RestaurantEntity> findRestaurantEntitiesByNameContains(String name);

    @Query(value = "select r from restaurant as r join bookmark_restaurant as br on r.id = br.idRestaurant where br.user.email = ?1")
    List<RestaurantEntity> findRestaurantBookmarkByUser(String email);

}
