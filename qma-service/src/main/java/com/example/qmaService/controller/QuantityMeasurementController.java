package com.example.qmaService.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.qmaService.dto.QuantityInputDTO;
import com.example.qmaService.entity.QuantityMeasurementEntity;
import com.example.qmaService.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api/v1/quantities")
@RequiredArgsConstructor
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

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
