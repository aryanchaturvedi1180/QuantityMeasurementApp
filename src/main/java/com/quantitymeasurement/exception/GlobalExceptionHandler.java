package com.quantitymeasurement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<String> handleCustomException(QuantityMeasurementException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return ResponseEntity.internalServerError().body("Something went wrong");
    }
}