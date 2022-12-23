package com.cybersoft.food_project.service;

import com.cybersoft.food_project.dto.OrderDetailDTO;
import com.cybersoft.food_project.dto.OrderPreviousDTO;
import com.cybersoft.food_project.dto.OrderUpcomingDTO;

import java.util.List;

public interface OrderService {

    List<OrderUpcomingDTO> getUpcomingOrderByEmail(String email);
    List<OrderPreviousDTO> getPreviousOrderByEmail(String email);
    OrderDetailDTO getOrderDetailById(int id);

}
