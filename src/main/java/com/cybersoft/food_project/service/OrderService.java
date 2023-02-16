package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.OrderCartDTO;
import com.cybersoft.food_project.dto.OrderDetailDTO;
import com.cybersoft.food_project.dto.OrderPreviousDTO;
import com.cybersoft.food_project.dto.OrderUpcomingDTO;

import java.util.List;

public interface OrderService {

    List<OrderUpcomingDTO> getUpcomingOrderByEmail();
    List<OrderPreviousDTO> getPreviousOrderByEmail();
    OrderCartDTO getCartOrderByEmail();
    boolean insertOrderCartByFoodId(int id);
    boolean deleteOrderCartByFoodId(int id);
    OrderDetailDTO getOrderDetailById(int id);

}
