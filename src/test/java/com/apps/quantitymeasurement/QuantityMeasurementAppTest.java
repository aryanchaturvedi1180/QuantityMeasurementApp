package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.0, Length.LengthUnit.FEET);

        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testInchesEquality() {
        Length inches1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length inches2 = new Length(1.0, Length.LengthUnit.INCHES);

        assertTrue(inches1.equals(inches2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length twelveInches = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(oneFoot.equals(twelveInches));
    }

    @Test
    public void testFeetInequality() {
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length twoFeet = new Length(2.0, Length.LengthUnit.FEET);

        assertFalse(oneFoot.equals(twoFeet));
    }

    @Test
    public void testInchesInequality() {
        Length oneInch = new Length(1.0, Length.LengthUnit.INCHES);
        Length twoInches = new Length(2.0, Length.LengthUnit.INCHES);

        assertFalse(oneInch.equals(twoInches));
    }

    @Test
    public void testCrossUnitInequality() {
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length tenInches = new Length(10.0, Length.LengthUnit.INCHES);

        assertFalse(oneFoot.equals(tenInches));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length twoFeet = new Length(2.0, Length.LengthUnit.FEET);
        Length twentyFourInches = new Length(24.0, Length.LengthUnit.INCHES);

        assertTrue(twoFeet.equals(twentyFourInches));
    }
}