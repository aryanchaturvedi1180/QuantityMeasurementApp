package com.apps.quantitymeasurement;

public enum LengthUnit {

	FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert unit → feet (base unit)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert feet → unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
    
   
}