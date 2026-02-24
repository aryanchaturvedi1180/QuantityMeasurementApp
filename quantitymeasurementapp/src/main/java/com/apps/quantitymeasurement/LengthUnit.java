package com.apps.quantitymeasurement;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.393701 / 12.0);

    private final double factorToFeet;

    LengthUnit(double factorToFeet) {
        this.factorToFeet = factorToFeet;
    }

    public double toFeet(double value) {
        return value * factorToFeet;
    }

    public double fromFeet(double feetValue) {
        return feetValue / factorToFeet;
    }

    public double getFactor() {
        return factorToFeet;
    }
}