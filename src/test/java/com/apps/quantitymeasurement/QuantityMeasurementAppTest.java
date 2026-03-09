package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    
	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.FEET);

	    assertEquals("2.0 FEET", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.INCHES);

	    assertEquals("24.0 INCHES", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.YARDS);

	    assertEquals("0.67 YARDS", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Centimeters() {
	    Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
	    Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.CENTIMETERS);

	    assertEquals("5.08 CENTIMETERS", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
	    Length l1 = new Length(2.0, Length.LengthUnit.YARDS);
	    Length l2 = new Length(3.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2, Length.LengthUnit.YARDS);

	    assertEquals("3.0 YARDS", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
	    Length l1 = new Length(2.0, Length.LengthUnit.YARDS);
	    Length l2 = new Length(3.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2, Length.LengthUnit.FEET);

	    assertEquals("9.0 FEET", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Commutativity() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result1 = l1.add(l2, Length.LengthUnit.YARDS);
	    Length result2 = l2.add(l1, Length.LengthUnit.YARDS);

	    assertEquals(result1.toString(), result2.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_WithZero() {
	    Length l1 = new Length(5.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.YARDS);

	    assertEquals("1.67 YARDS", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_NegativeValues() {
	    Length l1 = new Length(5.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2, Length.LengthUnit.INCHES);

	    assertEquals("36.0 INCHES", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    assertThrows(IllegalArgumentException.class, () -> {
	        l1.add(l2, null);
	    });
	}

	@Test
	public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
	    Length l1 = new Length(1000.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(500.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2, Length.LengthUnit.INCHES);

	    assertEquals("18000.0 INCHES", result.toString());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
	    Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.YARDS);

	    assertEquals("0.67 YARDS", result.toString());
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

	    Length.LengthUnit[] units = Length.LengthUnit.values();

	    for (Length.LengthUnit unit1 : units) {
	        for (Length.LengthUnit unit2 : units) {
	            for (Length.LengthUnit target : units) {

	                Length l1 = new Length(1.0, unit1);
	                Length l2 = new Length(1.0, unit2);

	                Length result = l1.add(l2, target);

	                assertNotNull(result);
	            }
	        }
	    }
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

	    double epsilon = 0.01;

	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2, Length.LengthUnit.YARDS);

	    assertEquals(0.67, Double.parseDouble(result.toString().split(" ")[0]), epsilon);


	    Length l3 = new Length(12.0, Length.LengthUnit.INCHES);
	    Length l4 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result2 = l3.add(l4, Length.LengthUnit.YARDS);

	    assertEquals(0.67, Double.parseDouble(result2.toString().split(" ")[0]), epsilon);
	}
}