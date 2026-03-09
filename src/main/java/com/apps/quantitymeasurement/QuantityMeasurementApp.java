package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        boolean result = q1.equals(q2);

        System.out.println(q1 + " == " + q2 + " ? " + result);

        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        Quantity<U> result = quantity.convertTo(targetUnit);

        System.out.println(quantity + " -> " + result);

        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2) {

        Quantity<U> result = q1.add(q2);

        System.out.println(q1 + " + " + q2 + " = " + result);

        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.add(q2, targetUnit);

        System.out.println(q1 + " + " + q2 + " = " + result);

        return result;
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12, LengthUnit.INCHES);

        demonstrateEquality(l1, l2);

        demonstrateConversion(l1, LengthUnit.YARDS);

        demonstrateAddition(l1, l2);

        demonstrateAddition(l1, l2, LengthUnit.YARDS);


        Quantity<WeightUnit> w1 =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000, WeightUnit.GRAM);

        demonstrateEquality(w1, w2);

        demonstrateConversion(w1, WeightUnit.POUND);

        demonstrateAddition(w1, w2);

        demonstrateAddition(w1, w2, WeightUnit.POUND);
    }
}