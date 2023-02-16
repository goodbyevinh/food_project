package com.cybersoft.food_project.repository;

import com.cybersoft.food_project.entity.FoodEntity;
import com.cybersoft.food_project.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findOrderEntitiesByUser_Email(String username);
    @Query(value = "select o from t_order as o join user as u on o.user.id = u.id " +
            "join order_status as os on o.id = os.order.id " +
            "where u.email = ?1 and ((os.current = true and os.idStatus = 1) or (os.current = true and os.idStatus = 2))")
    List<OrderEntity> findOrderEntitiesByUpcoming(String email);
    @Query(value = "select o from t_order as o join user as u on o.user.id = u.id " +
            "join order_status as os on o.id = os.order.id " +
            "where u.email = ?1 and ((os.current = true and os.idStatus = 3) or (os.current = true and os.idStatus = 4))")
    List<OrderEntity> findOrderEntitiesByPrevious(String email);
    @Query(value = "select o from t_order as o join user as u on o.user.id = u.id " +
            "where u.email = ?1 and o.cart = true")
    OrderEntity findOrderEntitiesByCart(String email);

    OrderEntity findOrderEntityById(int id);

}
