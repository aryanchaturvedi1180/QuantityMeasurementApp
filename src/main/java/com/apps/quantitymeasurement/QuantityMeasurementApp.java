package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

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

        System.out.println(value1 + " " + unit1 + " == " + value2 + " " + unit2 + " ? " + result);

        return result;
    }
    
    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
    	Length fromLength = new Length(value, fromUnit);
    	Length toLength = fromLength.convertTo(toUnit);
    	System.out.println(fromLength + " -> " + toLength);
    	return toLength;
    }
    
    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
    	Length convertedLength = length.convertTo(toUnit);
    	System.out.println(length + " -> " + convertedLength);
    	return convertedLength;
    }
    
    public static Length demonstrateLengthAddition(Length length1, Length length2) {

        Length result = length1.add(length2);

        System.out.println(length1 + " + " + length2 + " = " + result);

        return result;
    }
    
    public static Length demonstrateLengthAddition(
            Length length1, Length length2, LengthUnit targetUnit) {

        Length result = length1.add(length2, targetUnit);

        System.out.println(length1 + " + " + length2 + " = " + result);

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
        
        // Demonstrate Conversion from Centimeters to Feet
        demonstrateLengthConversion(
        		30, Length.LengthUnit.CENTIMETERS,
        		Length.LengthUnit.FEET
        );
        
        // Demonstrate Conversion from Yards to Inches
        demonstrateLengthConversion(
        		500, LengthUnit.YARDS,
        		LengthUnit.INCHES
        );
        
        // Demonstrate conversion from Feet to Inches
        Length fromLength = new Length(502, LengthUnit.FEET);
        demonstrateLengthConversion(fromLength, LengthUnit.INCHES);
        
        // Demonstrate Length Addition

        System.out.println("\n---- Length Addition Demonstrations ----");

        // 1 Foot + 12 Inches = 2 Feet
        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES)
        );

        // 1 Yard + 3 Feet
        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET)
        );

        // 100 Centimeters + 1 Foot
        demonstrateLengthAddition(
                new Length(100.0, Length.LengthUnit.CENTIMETERS),
                new Length(1.0, Length.LengthUnit.FEET)
        );

        // Same unit addition
        demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(3.0, Length.LengthUnit.FEET)
        );
        
        System.out.println("\n---- Length Addition With Target Unit ----");

        // 1 Foot + 12 Inches = result in Yards
        demonstrateLengthAddition(
        		new Length(1.0, LengthUnit.FEET),
        		new Length(12.0, LengthUnit.INCHES),
        		LengthUnit.YARDS
        );

        // 1 Yard + 3 Feet = result in Feet
        demonstrateLengthAddition(
        	new Length(1.0, LengthUnit.YARDS),
            new Length(3.0, LengthUnit.FEET),
            LengthUnit.FEET
        );

        // 100 cm + 1 foot = result in Inches
        demonstrateLengthAddition(
             new Length(100.0, LengthUnit.CENTIMETERS),
             new Length(1.0, LengthUnit.FEET),
             LengthUnit.INCHES
        );
        
    }
}