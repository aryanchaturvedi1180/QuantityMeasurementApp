package com.apps.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apps.quantitymeasurement.service.*;
import com.apps.quantitymeasurement.dto.*;
import com.apps.quantitymeasurement.entity.*;


@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public QuantityMeasurementEntity compare(@RequestBody QuantityInputDTO input) {
        return service.compare(input);
    }

    @PostMapping("/convert")
    public QuantityMeasurementEntity convert(@RequestBody QuantityInputDTO input) {
        return service.convert(input);
    }

    @PostMapping("/add")
    public QuantityMeasurementEntity add(@RequestBody QuantityInputDTO input) {
        return service.add(input);
    }
    
    @PostMapping("/subtract")
    public QuantityMeasurementEntity subtract(@RequestBody QuantityInputDTO input) {
        return service.subtract(input);
    }

    @PostMapping("/divide")
    public QuantityMeasurementEntity divide(@RequestBody QuantityInputDTO input) {
        return service.divide(input);
    }

    @GetMapping("/history/operation/{operation}")
    public List<QuantityMeasurementEntity> getHistory(@PathVariable String operation) {
        return service.getHistoryByOperation(operation);
    }

    @GetMapping("/count/{operation}")
    public long getCount(@PathVariable String operation) {
        return service.getOperationCount(operation);
    }
}