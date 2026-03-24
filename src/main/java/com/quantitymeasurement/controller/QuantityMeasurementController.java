package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.QuantityDTO;
import java.util.List;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityDTO[] quantities){
        return service.compare(quantities[0],quantities[1]);
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO quantity,
                               @RequestParam String targetUnit){
        return service.convert(quantity,targetUnit);
    }

    @PostMapping("/add")
    public QuantityDTO add(@RequestBody QuantityDTO[] quantities){
        return service.add(quantities[0],quantities[1]);
    }

    @PostMapping("/subtract")
    public QuantityDTO subtract(@RequestBody QuantityDTO[] quantities){
        return service.subtract(quantities[0],quantities[1]);
    }

    @PostMapping("/divide")
    public double divide(@RequestBody QuantityDTO[] quantities){
        return service.divide(quantities[0],quantities[1]);
    }
    
    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementEntity> getHistory(@PathVariable String operation){
        return service.getHistory(operation);
    }

    @GetMapping("/count/{operation}")
    public long getCount(@PathVariable String operation){
        return service.getCount(operation);
    }
}