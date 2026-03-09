package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    // Feet tests
    @Test
    void testFeet_SameValue() {
    	
        assertTrue(QuantityMeasurementApp.areFeetEqual(1.0, 1.0));
    }

    @Test
    void testFeet_DifferentValue() {
    	
        assertFalse(QuantityMeasurementApp.areFeetEqual(1.0, 2.0));
    }

    @Test
    void testFeet_NullComparison() {
    	
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void testFeet_SameReference() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    void testFeet_NonNumeric() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals("1.0"));
    }

    // Inches tests
    @Test
    void testInches_SameValue() {
        assertTrue(QuantityMeasurementApp.areInchesEqual(1.0, 1.0));
    }

    @Test
    void testInches_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areInchesEqual(1.0, 2.0));
    }

    @Test
    void testInches_NullComparison() {
    	
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(i.equals(null));
    }

    @Test
    void testInches_SameReference() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(i.equals(i));
        
    }

    @Test
    void testInches_NonNumeric() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(i.equals("1.0"));
    }
}