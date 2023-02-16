package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.dto.OrderCartDTO;
import com.cybersoft.food_project.dto.OrderDetailDTO;
import com.cybersoft.food_project.dto.OrderPreviousDTO;
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
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    OrderService orderService;

    @GetMapping("/upcoming")
    ResponseEntity<DataResponse> getOrdersUpcoming() {

        List<OrderUpcomingDTO> orders = orderService.getUpcomingOrderByEmail();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(orders);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/previous")
    ResponseEntity<DataResponse> getOrdersPrevious() {

        List<OrderPreviousDTO> orders = orderService.getPreviousOrderByEmail();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(orders);
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
    @GetMapping("/get-cart")
    ResponseEntity<DataResponse> getOrderCart() {
        OrderCartDTO order = orderService.getCartOrderByEmail();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(true);
        dataResponse.setData(order);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/insert-cart")
    ResponseEntity<DataResponse> insertCart(@RequestParam("id") int id) {
        boolean isSucess = orderService.insertOrderCartByFoodId(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(isSucess);

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/delete-cart")
    ResponseEntity<DataResponse> deleteCart(@RequestParam("id") int id) {
        boolean isSucess = orderService.deleteOrderCartByFoodId(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(isSucess);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }


}
