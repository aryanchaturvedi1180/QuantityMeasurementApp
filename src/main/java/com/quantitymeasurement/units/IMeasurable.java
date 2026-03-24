package com.quantitymeasurement.units;

@FunctionalInterface
interface SupportsArithmetic{
    boolean isSupported();
}

public interface IMeasurable {

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double value);

    String getUnitName();

    double getConversionFactor();
    
    SupportsArithmetic supportsArithmetic=()->true;
    
    default boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation){
    }
}