package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.OrderCartDTO;
import com.cybersoft.food_project.dto.OrderDetailDTO;
import com.cybersoft.food_project.dto.OrderPreviousDTO;
import com.cybersoft.food_project.dto.OrderUpcomingDTO;
import com.cybersoft.food_project.entity.FoodOrderEntity;
import com.cybersoft.food_project.entity.OrderEntity;
import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.model.FoodModel;
import com.cybersoft.food_project.model.FoodOrderModel;
import com.cybersoft.food_project.model.RestaurantModel;
import com.cybersoft.food_project.repository.OrderRepository;
import com.cybersoft.food_project.repository.UserRepository;
import com.cybersoft.food_project.utils.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Context context;

    @Override
    public List<OrderUpcomingDTO> getUpcomingOrderByEmail() {
        String email =  context.getEmail();
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByUpcoming(email);
        List<OrderUpcomingDTO> list = new ArrayList<>();
        orders.forEach(orderEntity -> {
            OrderUpcomingDTO orderUpcomingDTO = new OrderUpcomingDTO();
            orderUpcomingDTO.setId(orderEntity.getId());
            String status = orderEntity.getOrderStatuses().stream()
                    .filter(orderStatusEntity -> orderStatusEntity.isCurrent())
                    .findFirst().get().getStatus().getName();
            orderUpcomingDTO.setStatus(status);
            orderUpcomingDTO.setTime(orderEntity.getEstimateShip());

            List<FoodOrderModel> foodOrderModelList = new ArrayList<>();
            orderEntity.getFoodOrders().forEach(foodOrderEntity -> {
                FoodOrderModel foodOrderModel = new FoodOrderModel();
                foodOrderModel.setName(foodOrderEntity.getFood().getName());
                foodOrderModel.setQuantity(foodOrderEntity.getQuantity());
                foodOrderModelList.add(foodOrderModel);
            });
            orderUpcomingDTO.setFoods(foodOrderModelList);
            list.add(orderUpcomingDTO);
        });
        return list;
    }

    @Override
    public List<OrderPreviousDTO> getPreviousOrderByEmail() {
        String email =  context.getEmail();
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByPrevious(email);
        List<OrderPreviousDTO> list = new ArrayList<>();
        orders.forEach(orderEntity -> {
            OrderPreviousDTO orderPreviousDTO = new OrderPreviousDTO();
            orderPreviousDTO.setId(orderEntity.getId());
            String status = orderEntity.getOrderStatuses().stream()
                    .filter(orderStatusEntity -> orderStatusEntity.isCurrent())
                    .findFirst().get().getStatus().getName();
            orderPreviousDTO.setStatus(status);
            orderPreviousDTO.setTime(orderEntity.getEstimateShip());
            List<FoodOrderModel> foodOrderModelList = new ArrayList<>();
            orderEntity.getFoodOrders().forEach(foodOrderEntity -> {
                FoodOrderModel foodOrderModel = new FoodOrderModel();
                foodOrderModel.setName(foodOrderEntity.getFood().getName());
                foodOrderModel.setQuantity(foodOrderEntity.getQuantity());
                foodOrderModelList.add(foodOrderModel);
            });
            orderPreviousDTO.setFoods(foodOrderModelList);
            list.add(orderPreviousDTO);

        });
        return list;
    }

    @Override
    public OrderCartDTO getCartOrderByEmail() {
        String email =  context.getEmail();
        OrderEntity order = orderRepository.findOrderEntitiesByCart(email);
        OrderCartDTO orderCartDTO = new OrderCartDTO();
        List<FoodModel> foods = new ArrayList<>();
        AtomicReference<Float> checkout = new AtomicReference<>((float) 0);
        AtomicReference<Float> fee = new AtomicReference<>((float) 0);
        order.getFoodOrders().forEach(foodOrderEntity -> {
            FoodModel food = new FoodModel();
            food.setName(foodOrderEntity.getFood().getName());
            food.setId(foodOrderEntity.getFood().getId());
            food.setImage(foodOrderEntity.getFood().getImage());
            food.setPrice(foodOrderEntity.getFood().getPrice());
            food.setQuantity(foodOrderEntity.getQuantity());
            checkout.set(checkout.get() + foodOrderEntity.getPrice() * foodOrderEntity.getQuantity());
            fee.set(fee.get() + foodOrderEntity.getFood().getRestaurant().getFee());
            foods.add(food);
        });
        orderCartDTO.setFoods(foods);
        orderCartDTO.setCheckout(checkout.get());
        orderCartDTO.setShipFee(fee.get());
        return orderCartDTO;

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
            orderDetailDTO.setSubTotal(orderDetailDTO.getSubTotal() + foodOrder.getPrice() * foodOrder.getQuantity());
            map.put(foodOrder.getFood().getName() , foodOrder.getQuantity());
        });
        orderDetailDTO.setTotal(orderDetailDTO.getFee() + orderDetailDTO.getSubTotal());
        return orderDetailDTO;
    }

    @Override
    public boolean insertOrderCartByFoodId(int id) {
        String email =  context.getEmail();

        OrderEntity order = orderRepository.findOrderEntitiesByCart(email);
        boolean isExist = order.getFoodOrders().stream().anyMatch(foodOrderEntity -> foodOrderEntity.getIdFood() == id);
        if (isExist) {

            Optional<FoodOrderEntity> foodOrder = order.getFoodOrders().stream()
                    .filter(foodOrderEntity -> foodOrderEntity.getIdFood() == id)
                    .findFirst();
            if (foodOrder.isPresent()) {
                foodOrder.get().setQuantity(foodOrder.get().getQuantity() + 1);
            }
        } else {
            FoodOrderEntity foodOrderEntity = new FoodOrderEntity();
            foodOrderEntity.setIdFood(id);
            foodOrderEntity.setIdOrder(order.getId());
            order.getFoodOrders().add(foodOrderEntity);
        }
        try {
            orderRepository.save(order);
            return true;
        } catch ( Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteOrderCartByFoodId(int id) {
        String email =  context.getEmail();

        OrderEntity order = orderRepository.findOrderEntitiesByCart(email);
        boolean isExist = order.getFoodOrders().stream().anyMatch(foodOrderEntity -> foodOrderEntity.getIdFood() == id);
        if (isExist) {
            Optional<FoodOrderEntity> foodOrder = order.getFoodOrders().stream()
                    .filter(foodOrderEntity -> foodOrderEntity.getIdFood() == id)
                    .findFirst();
            if (foodOrder.isPresent()) {
                if (foodOrder.get().getQuantity() > 1) {
                    foodOrder.get().setQuantity(foodOrder.get().getQuantity() - 1);
                } else {
                    order.getFoodOrders().remove(foodOrder.get());
                }
            }
        } else {
            // throw
        }
        try {
            orderRepository.save(order);
            return true;
        } catch ( Exception e) {
            return false;
        }
    }
}
