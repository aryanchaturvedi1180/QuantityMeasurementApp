package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.Quantity.ArithmeticOperation;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.01;

	@Test
	void testRefactoring_Add_DelegatesViaHelper() {
		Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.add(q2);

		assertEquals(15.0, result.getValue(), EPSILON);
	}

	@Test
	void testRefactoring_Subtract_DelegatesViaHelper() {
		Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.subtract(q2);

		assertEquals(5.0, result.getValue(), EPSILON);
	}

	@Test
	void testRefactoring_Divide_DelegatesViaHelper() {
		Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

		double result = q1.divide(q2);

		assertEquals(2.0, result, EPSILON);
	}

	@Test
	void testImplicitTargetUnit_AddSubtract() {
		Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.add(q2);

		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	@Test
	void testExplicitTargetUnit_AddSubtract_Overrides() {
		Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

		assertEquals(114.0, result.getValue(), EPSILON);
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	@Test
	void testValidation_NullOperand_ConsistentAcrossOperations() {

		Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> q.add(null));
		assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
		assertThrows(IllegalArgumentException.class, () -> q.divide(null));
	}

	@Test
	void testImmutability_AfterAdd_ViaCentralizedHelper() {

		Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

		q1.add(q2);

		assertEquals(10, q1.getValue());
		assertEquals(5, q2.getValue());
	}

	@Test
	void testRounding_Helper_Accuracy() {

		Quantity<LengthUnit> q1 = new Quantity<>(1.234567, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q1.add(q2);

		assertEquals(1.23, result.getValue(), 0.01);
	}

	@Test
	void testArithmeticOperation_Add_EnumComputation() {
		double result = ArithmeticOperation.ADD.compute(10, 5);
		assertEquals(15.0, result);
	}

	@Test
	void testArithmeticOperation_Subtract_EnumComputation() {
		double result = ArithmeticOperation.SUBTRACT.compute(10, 5);
		assertEquals(5.0, result);
	}

	@Test
	void testArithmeticOperation_Divide_EnumComputation() {
		double result = ArithmeticOperation.DIVIDE.compute(10, 5);
		assertEquals(2.0, result);
	}

	@Test
	void testArithmeticOperation_DivideByZero_EnumThrows() {
		assertThrows(ArithmeticException.class, () -> ArithmeticOperation.DIVIDE.compute(10, 0));
	}

}