package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {

        double original = 5.0;

        double converted =
                QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCHES);

        double roundTrip =
                QuantityMeasurementApp.convert(converted, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(original, roundTrip, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }
}