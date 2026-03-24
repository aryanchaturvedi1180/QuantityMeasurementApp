package com.quantitymeasurement.service;

import java.util.*;
import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1,QuantityDTO q2);

    QuantityDTO convert(QuantityDTO source,String targetUnit);

    QuantityDTO add(QuantityDTO q1,QuantityDTO q2);

    QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2);

    double divide(QuantityDTO q1,QuantityDTO q2);
    
    List<QuantityMeasurementEntity> getHistory(String operation);

    long getCount(String operation);
    
    void deleteById(Long id);
}