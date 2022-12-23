package com.cybersoft.food_project.exception.advice;

import com.cybersoft.food_project.exception.DevineZeroException;
import com.cybersoft.food_project.payload.response.DataResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DevineZeroAdviceController {

    Logger logger = LoggerFactory.getLogger(DevineZeroAdviceController.class);
    Gson gson = new Gson();
    //@ExceptionHandler({DevineZeroException.class, RuntimeException.class})
    @ExceptionHandler(DevineZeroException.class)
    public ResponseEntity<DataResponse> handleDevineZeroException(Exception message) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dataResponse.setDesc("Error /0" + message.getMessage());
        logger.error(gson.toJson(dataResponse));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
