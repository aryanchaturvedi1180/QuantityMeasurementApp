package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static void demonstrateFeetEquality() {
        Length oneFootA = new Length(1.0, Length.LengthUnit.FEET);
        Length oneFootB = new Length(1.0, Length.LengthUnit.FEET);

        boolean equal = demonstrateLengthEquality(oneFootA, oneFootB);
        System.out.println("Feet equality: 1.0 ft == 1.0 ft ? " + equal);
    }

    public static void demonstrateInchesEquality() {
        Length oneInchA = new Length(1.0, Length.LengthUnit.INCHES);
        Length oneInchB = new Length(1.0, Length.LengthUnit.INCHES);

        boolean equal = demonstrateLengthEquality(oneInchA, oneInchB);
        System.out.println("Inches equality: 1.0 in == 1.0 in ? " + equal);
    }

    public static void demonstrateFeetInchesComparison() {
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length twelveInches = new Length(12.0, Length.LengthUnit.INCHES);

        boolean equal = demonstrateLengthEquality(oneFoot, twelveInches);
        System.out.println("Feet-Inches comparison: 1.0 ft == 12.0 in ? " + equal);
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}