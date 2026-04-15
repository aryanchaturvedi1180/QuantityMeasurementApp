package com.example.qmaService.units;

public interface IMeasurable {

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double value);

    String getUnitName();

    double getConversionFactor();

    boolean supportsArithmetic();
}