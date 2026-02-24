package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        double base = source.toFeet(value);
        return target.fromFeet(base);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {

        double result = convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength length, LengthUnit to) {

        QuantityLength converted = length.convertTo(to);
        System.out.println(length + " -> " + converted);
    }

    public static void demonstrateLengthEquality(QuantityLength l1, QuantityLength l2) {

        System.out.println(l1 + " == " + l2 + " ? " + l1.equals(l2));
    }
}