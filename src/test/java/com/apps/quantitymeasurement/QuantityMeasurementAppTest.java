package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void feetToInches() {
    	
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length result = feet.convertTo(Length.LengthUnit.INCHES);
        assertEquals("12.0 INCHES", result.toString());
    }

    @Test
    public void inchesToFeet() {
    	
        Length inches = new Length(24.0, Length.LengthUnit.INCHES);
        Length result = inches.convertTo(Length.LengthUnit.FEET);
        assertEquals("2.0 FEET", result.toString());
    }

    @Test
    public void yardsToInches() {
        Length yards = new Length(1.0, Length.LengthUnit.YARDS);
        Length result = yards.convertTo(Length.LengthUnit.INCHES);
        assertEquals("36.0 INCHES", result.toString());
    }

    @Test
    public void inchesToYards() {
    	
        Length inches = new Length(72.0, Length.LengthUnit.INCHES);
        Length result = inches.convertTo(Length.LengthUnit.YARDS);
        assertEquals("2.0 YARDS", result.toString());
    }

    @Test
    public void feetToYards() {
        Length feet = new Length(6.0, Length.LengthUnit.FEET);
        Length result = feet.convertTo(Length.LengthUnit.YARDS);
        assertEquals("2.0 YARDS", result.toString());
    }

    @Test
    public void zeroConversion() {
        Length zero = new Length(0.0, Length.LengthUnit.FEET);
        Length result = zero.convertTo(Length.LengthUnit.INCHES);
        assertEquals("0.0 INCHES", result.toString());
    }

    
    @Test
    public void negativeConversion() {
        Length negative = new Length(-1.0, Length.LengthUnit.FEET);
        Length result = negative.convertTo(Length.LengthUnit.INCHES);
        assertEquals("-12.0 INCHES", result.toString());
    }

    
    @Test
    public void sameUnitConversion() {
        Length feet = new Length(5.0, Length.LengthUnit.FEET);
        Length result = feet.convertTo(Length.LengthUnit.FEET);
        assertEquals("5.0 FEET", result.toString());
    }

    @Test
    public void roundTripConversion() {
        Length original = new Length(3.0, Length.LengthUnit.YARDS);

        Length inches = original.convertTo(Length.LengthUnit.INCHES);
        Length back = inches.convertTo(Length.LengthUnit.YARDS);

        assertTrue(original.equals(back));
    }

    
    @Test
    public void nullTargetUnitThrows() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            feet.convertTo(null);
        });
    }
}