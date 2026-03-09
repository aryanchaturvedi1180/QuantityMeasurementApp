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
        
    }
}