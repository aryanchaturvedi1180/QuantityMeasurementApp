package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO convert(QuantityDTO source, String targetUnit);

    QuantityDTO add(QuantityDTO q1,QuantityDTO q2);

    QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2);

    double divide(QuantityDTO q1,QuantityDTO q2);
}