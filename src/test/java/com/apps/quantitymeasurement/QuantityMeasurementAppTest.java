package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {
	
	@Test
    void testFeetEquality_SameValue() {
		QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(5.0);
		QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(5.0);

        assertTrue(feet1.equals(feet2));
    }
	
	@Test
    void testFeetEquality_DifferentValue() {
		QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(5.0);
		QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(6.0);

        assertFalse(feet1.equals(feet2));
    }
	
	@Test
    void testFeetEquality_SameReference() {
		QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(5.0);

        assertTrue(feet1.equals(feet1));
    }
	
	@Test
    void testFeetEquality_NullComparison() {
		QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(5.0);

        assertFalse(feet1.equals(null));
    }
	
	void testFeetEquality_DifferentObjectType() {
		QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(5.0);

        assertFalse(feet1.equals("5.0"));
    }
}