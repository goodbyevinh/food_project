package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.dto.OrderDetailDTO;
import com.cybersoft.food_project.dto.OrderUpcomingDTO;
import com.cybersoft.food_project.jwt.JwtTokenHelper;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.cybersoft.food_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    OrderService orderService;

    @GetMapping("/upcoming")
    ResponseEntity<DataResponse> getOrdersUpcoming(HttpServletRequest request) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = (String) securityContext.getAuthentication().getPrincipal();
        List<OrderUpcomingDTO> orders = orderService.getUpcomingOrderByEmail(username);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        //dataResponse.setData(orders);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/detail")
    ResponseEntity<DataResponse> getOrderDetails(@RequestParam("id") int id) {
        OrderDetailDTO order = orderService.getOrderDetailById(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(order);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
