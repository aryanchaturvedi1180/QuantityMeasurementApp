package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
    	
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(a.equals(b));
    }

    @Test
    public void testInchesEquality() {
        Length a = new Length(5.0, Length.LengthUnit.INCHES);
        Length b = new Length(5.0, Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
    }

    @Test
    public void testFeetInchesComparison() {
    	
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
    }

    @Test
    public void testFeetInequality() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(a.equals(b));
    }

    @Test
    public void testInchesInequality() {
        Length a = new Length(10.0, Length.LengthUnit.INCHES);
        Length b = new Length(11.0, Length.LengthUnit.INCHES);
        assertFalse(a.equals(b));
    }

    @Test
    public void testCrossUnitInequality() {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(10.0, Length.LengthUnit.INCHES);
        assertFalse(a.equals(b));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length a = new Length(2.0, Length.LengthUnit.FEET);
        Length b = new Length(24.0, Length.LengthUnit.INCHES);
        assertTrue(a.equals(b));
    }

    @Test
    public void yardEquals36Inches() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length cm = new Length(100.0, Length.LengthUnit.CENTIMETERS);
        Length inches = new Length(39.3701, Length.LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(feet.equals(yard));
    }

    @Test
    public void yardNotEqualToInches() {
    	
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length inches = new Length(30.0, Length.LengthUnit.INCHES);
        assertFalse(yard.equals(inches));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length a = new Length(5.0, Length.LengthUnit.FEET);
        Length b = a;
        assertTrue(a.equals(b));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length a = new Length(5.0, Length.LengthUnit.FEET);
        assertFalse(a.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length a = new Length(1.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);
        Length c = new Length(36.0, Length.LengthUnit.INCHES);

        // reflexive
        assertTrue(a.equals(a));

        // symmetric
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // transitive
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        Length a = new Length(4.0, Length.LengthUnit.YARDS);
        Length b = new Length(5.0, Length.LengthUnit.YARDS);
        assertFalse(a.equals(b));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        boolean result = QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, Length.LengthUnit.FEET,
                12.0, Length.LengthUnit.INCHES
        );
        assertTrue(result);
    }
}