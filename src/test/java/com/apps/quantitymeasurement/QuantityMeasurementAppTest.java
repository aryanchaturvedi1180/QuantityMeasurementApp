package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementAppTest {

	private QuantityMeasurementServiceImpl service;

	@BeforeEach
	void setUp() {
		service = new QuantityMeasurementServiceImpl(QuantityMeasurementCacheRepository.getInstance());
	}

	// ---------------- COMPARISON TESTS ----------------

	@Test
	void testFeetEqualsInches() {
		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

		assertTrue(service.compare(q1, q2));
	}

	@Test
	void testFeetNotEqualsDifferentLength() {
		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(24.0, "INCHES", "LENGTH");

		assertFalse(service.compare(q1, q2));
	}

	@Test
	void testSameValuesEqual() {
		QuantityDTO q1 = new QuantityDTO(5.0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(5.0, "FEET", "LENGTH");

		assertTrue(service.compare(q1, q2));
	}

	@Test
	void testCompareNullFirst() {
		QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

		assertThrows(IllegalArgumentException.class, () -> service.compare(null, q2));
	}

	@Test
	void testCompareNullSecond() {
		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");

		assertThrows(IllegalArgumentException.class, () -> service.compare(q1, null));
	}

	// ---------------- ADDITION TESTS ----------------

	@Test
	void testAdditionFeetAndInches() {
		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

		QuantityDTO result = service.add(q1, q2);

		assertEquals(13.0, result.getValue());
	}

	@Test
	void testAdditionSameUnit() {
		QuantityDTO q1 = new QuantityDTO(5.0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(3.0, "FEET", "LENGTH");

		QuantityDTO result = service.add(q1, q2);

		assertEquals(8.0, result.getValue());
	}

	@Test
	void testAdditionWithZero() {
		QuantityDTO q1 = new QuantityDTO(5.0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(0.0, "FEET", "LENGTH");

		QuantityDTO result = service.add(q1, q2);

		assertEquals(5.0, result.getValue());
	}

	@Test
	void testAdditionLargeNumbers() {
		QuantityDTO q1 = new QuantityDTO(1000000, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(2000000, "FEET", "LENGTH");

		QuantityDTO result = service.add(q1, q2);

		assertEquals(3000000, result.getValue());
	}

	@Test
	void testAdditionNegativeNumbers() {
		QuantityDTO q1 = new QuantityDTO(-5, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(3, "FEET", "LENGTH");

		QuantityDTO result = service.add(q1, q2);

		assertEquals(-2, result.getValue());
	}

	// ---------------- SUBTRACTION TESTS ----------------

	@Test
	void testSubtractionNormal() {
		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(5, "FEET", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(5, result.getValue());
	}

	@Test
	void testSubtractionNegativeResult() {
		QuantityDTO q1 = new QuantityDTO(5, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(10, "FEET", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(-5, result.getValue());
	}

	@Test
	void testSubtractionZeroResult() {
		QuantityDTO q1 = new QuantityDTO(5, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(5, "FEET", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(0, result.getValue());
	}

	@Test
	void testSubtractionWithZero() {
		QuantityDTO q1 = new QuantityDTO(5, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(0, "FEET", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(5, result.getValue());
	}

	@Test
	void testSubtractionDecimalValues() {
		QuantityDTO q1 = new QuantityDTO(5.5, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(2.5, "FEET", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(3.0, result.getValue());
	}

	// ---------------- DIVISION TESTS ----------------

	@Test
	void testDivisionNormal() {
		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(2, "FEET", "LENGTH");

		assertEquals(5, service.divide(q1, q2));
	}

	@Test
	void testDivisionDecimalResult() {
		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(4, "FEET", "LENGTH");

		assertEquals(2.5, service.divide(q1, q2));
	}

	@Test
	void testDivisionOne() {
		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(10, "FEET", "LENGTH");

		assertEquals(1, service.divide(q1, q2));
	}

	@Test
	void testDivisionByZero() {
		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(0, "FEET", "LENGTH");

		assertThrows(ArithmeticException.class, () -> service.divide(q1, q2));
	}

	@Test
	void testDivisionZeroNumerator() {
		QuantityDTO q1 = new QuantityDTO(0, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(5, "FEET", "LENGTH");

		assertEquals(0, service.divide(q1, q2));
	}

	// ---------------- CONVERSION TESTS ----------------

	@Test
	void testConvertFeetToInches() {
		QuantityDTO q = new QuantityDTO(1, "FEET", "LENGTH");

		QuantityDTO result = service.convert(q, "INCHES");

		assertEquals("INCHES", result.getUnit());
	}

	@Test
	void testConvertFeetToFeet() {
		QuantityDTO q = new QuantityDTO(5, "FEET", "LENGTH");

		QuantityDTO result = service.convert(q, "FEET");

		assertEquals("FEET", result.getUnit());
	}

	@Test
	void testConvertNullInput() {
		assertThrows(IllegalArgumentException.class, () -> service.convert(null, "INCHES"));
	}

	@Test
	void testConvertLargeValue() {
		QuantityDTO q = new QuantityDTO(10000, "FEET", "LENGTH");

		QuantityDTO result = service.convert(q, "INCHES");

		assertEquals("INCHES", result.getUnit());
	}

	// ---------------- EDGE CASE TESTS ----------------

	@Test
	void testVerySmallValues() {
		QuantityDTO q1 = new QuantityDTO(0.001, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(0.0005, "FEET", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(0.0005, result.getValue());
	}

	@Test
	void testNegativeComparison() {
		QuantityDTO q1 = new QuantityDTO(-5, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(-5, "FEET", "LENGTH");

		assertTrue(service.compare(q1, q2));
	}

	@Test
	void testLargeSubtraction() {
		QuantityDTO q1 = new QuantityDTO(1000000, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(500000, "FEET", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(500000, result.getValue());
	}

}