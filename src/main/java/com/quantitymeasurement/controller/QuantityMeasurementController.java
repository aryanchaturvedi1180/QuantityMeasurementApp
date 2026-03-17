package com.quantitymeasurement.controller;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class QuantityMeasurementController {

    private final QuantityMeasurementServiceImpl service;
    @Autowired
    public QuantityMeasurementController(QuantityMeasurementServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public QuantityMeasurementEntity save(@RequestBody QuantityMeasurementEntity entity) {
       return service.save(entity);

    }

    @GetMapping
    public List<QuantityMeasurementEntity> getAll() {
        return service.findAll();
    }
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return "Measurement with ID " + id + " deleted";
    }
    
    @DeleteMapping
    public String deleteAll(){
        service.deleteAllMeasurements();
        return "All the measurements Deleted";
    }
}