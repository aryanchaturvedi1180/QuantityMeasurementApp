package com.example.qmaService.exception;

public class DivisionByZeroException extends QuantityMeasurementException {

    public DivisionByZeroException(String message) {
        super(message);
    }
}