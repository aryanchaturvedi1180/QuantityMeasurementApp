package com.example.qmaService.service;

import java.util.List;

import com.example.qmaService.dto.QuantityInputDTO;
import com.example.qmaService.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

	QuantityMeasurementEntity convert(QuantityInputDTO input);

    QuantityMeasurementEntity add(QuantityInputDTO input);
    
    QuantityMeasurementEntity subtract(QuantityInputDTO input);
    
    QuantityMeasurementEntity divide(QuantityInputDTO input);

    List<QuantityMeasurementEntity> getHistoryByOperation(String operation);

    long getOperationCount(String operation);

	QuantityMeasurementEntity compare(QuantityInputDTO input);
}
