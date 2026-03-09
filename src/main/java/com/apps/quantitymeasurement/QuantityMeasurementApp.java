package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(
            double value1, Length.LengthUnit unit1,
            double value2, Length.LengthUnit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        boolean result = demonstrateLengthEquality(length1, length2);

        System.out.println(value1 + " " + unit1 +" == " + value2 + " " + unit2 + " ? " + result);

        return result;
    }

    public static void main(String[] args) {

        // Demonstrate Feet and Inches comparison
        demonstrateLengthComparison(
                1.0, Length.LengthUnit.FEET,
                12.0, Length.LengthUnit.INCHES
        );

        // Demonstrate Yards and Inches comparison
        demonstrateLengthComparison(
                1.0, Length.LengthUnit.YARDS,
                36.0, Length.LengthUnit.INCHES
        );

        // Demonstrate Centimeters and Inches comparison
        demonstrateLengthComparison(
                100.0, Length.LengthUnit.CENTIMETERS,
                39.3701, Length.LengthUnit.INCHES
        );

        // Demonstrate Feet and Yards comparison
        demonstrateLengthComparison(
                3.0, Length.LengthUnit.FEET,
                1.0, Length.LengthUnit.YARDS
        );

        // Demonstrate Centimeters and Feet comparison
        demonstrateLengthComparison(
                30.48, Length.LengthUnit.CENTIMETERS,
                1.0, Length.LengthUnit.FEET
        );
    }
}