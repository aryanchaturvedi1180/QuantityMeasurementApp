package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        boolean result = demonstrateLengthEquality(length1, length2);

        System.out.println(value1 + " " + unit1 + " == " + value2 + " " + unit2 + " ? " + result);

        return result;
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {

        Length fromLength = new Length(value, fromUnit);

        Length result = fromLength.convertTo(toUnit);

        System.out.println(fromLength + " -> " + result);

        return result;
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2) {

        Length result = length1.add(length2);

        System.out.println(length1 + " + " + length2 + " = " + result);

        return result;
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit) {

        Length result = l1.add(l2, targetUnit);

        System.out.println(l1 + " + " + l2 + " = " + result);

        return result;
    }

    public static void main(String[] args) {

        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);

        demonstrateLengthConversion(30, LengthUnit.CENTIMETERS, LengthUnit.FEET);

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES)
        );

        demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );
    }
}