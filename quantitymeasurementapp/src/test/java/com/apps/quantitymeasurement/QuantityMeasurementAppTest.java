package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

	@Test
	void testFeetEquality_SameValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

		assertEquals(f1, f2);
	}

	@Test
	void testFeetEquality_DifferentValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

		assertNotEquals(f1, f2);
	}

	@Test
	void testFeetEquality_NullComparison() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

		assertNotEquals(f1, null);
	}

	@Test
	void testFeetEquality_SameReference() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

		assertEquals(f1, f1);
	}

	@Test
	void testFeetEquality_NonNumericInput() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

		assertNotEquals(f1, "Not a Feet Object");
	}
}
