package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.01;

	@Test
	void testSubtraction_SameUnit_FeetMinusFeet() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(5.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	@Test
	void testSubtraction_SameUnit_LitreMinusLitre() {
		Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> q2 = new Quantity<>(3.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = q1.subtract(q2);

		assertEquals(7.0, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_CrossUnit_FeetMinusInches() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(9.5, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_ExplicitTargetUnit_Inches() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

		assertEquals(114.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	@Test
	void testSubtraction_ResultingInNegative() {
		Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(-5.0, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_ResultingInZero() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(0.0, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_WithZeroOperand() {
		Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(5.0, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_NullOperand() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
	}

	@Test
	void testSubtraction_ChainedOperations() {
		Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
				.subtract(new Quantity<>(2.0, LengthUnit.FEET)).subtract(new Quantity<>(1.0, LengthUnit.FEET));

		assertEquals(7.0, result.getValue(), EPSILON);
	}

	@Test
	void testSubtraction_Immutability() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

		q1.subtract(q2);

		assertEquals(10.0, q1.getValue());
		assertEquals(5.0, q2.getValue());
	}

	@Test
	void testDivision_SameUnit_FeetDividedByFeet() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

		double result = q1.divide(q2);

		assertEquals(5.0, result, EPSILON);
	}

	@Test
	void testDivision_RatioGreaterThanOne() {
		double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET));

		assertTrue(result > 1);
	}

	@Test
	void testDivision_RatioLessThanOne() {
		double result = new Quantity<>(5.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));

		assertEquals(0.5, result, EPSILON);
	}

	@Test
	void testDivision_RatioEqualToOne() {
		double result = new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(10.0, LengthUnit.FEET));

		assertEquals(1.0, result, EPSILON);
	}

	@Test
	void testDivision_ByZero() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

		assertThrows(ArithmeticException.class, () -> q1.divide(q2));
	}

	@Test
	void testDivision_NullOperand() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
	}

	@Test
	void testDivision_CrossCategory() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

		assertThrows(IllegalArgumentException.class, () -> q1.divide((Quantity) q2));
	}

	@Test
	void testDivision_Immutability() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

		q1.divide(q2);

		assertEquals(10.0, q1.getValue());
		assertEquals(5.0, q2.getValue());
	}
}