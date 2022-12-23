package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.OrderDetailDTO;
import com.cybersoft.food_project.dto.OrderPreviousDTO;
import com.cybersoft.food_project.dto.OrderUpcomingDTO;
import com.cybersoft.food_project.entity.OrderEntity;
import com.cybersoft.food_project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderUpcomingDTO> getUpcomingOrderByEmail(String email) {
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByUpcoming(email);

        return null;
    }

    @Override
    public List<OrderPreviousDTO> getPreviousOrderByEmail(String email) {
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByPrevious(email);
        return null;
    }

    @Override
    public OrderDetailDTO getOrderDetailById(int id) {
        OrderEntity order = orderRepository.findOrderEntityById(id);
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setFee(order.getFee());
        orderDetailDTO.setAddress(order.getDeliverAddress());
        orderDetailDTO.setRestaurantName(order.getFoodOrders().iterator().next().getFood().getRestaurant().getName());
        orderDetailDTO.setSubTotal(0);
        Map<String, Integer> map = new HashMap<>();
        order.getFoodOrders().forEach(foodOrder -> {
            orderDetailDTO.setSubTotal(orderDetailDTO.getSubTotal() + foodOrder.getPrice() * foodOrder.getQuality());
            map.put(foodOrder.getFood().getName() , foodOrder.getQuality());
        });
        orderDetailDTO.setTotal(orderDetailDTO.getFee() + orderDetailDTO.getSubTotal());
        return orderDetailDTO;
    }


}
