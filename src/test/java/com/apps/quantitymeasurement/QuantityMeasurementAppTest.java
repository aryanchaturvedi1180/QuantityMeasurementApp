package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    
	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(2.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    assertEquals("3.0 FEET", result.toString());
	}

	@Test
	public void testAddition_SameUnit_InchPlusInch() {
	    Length l1 = new Length(6.0, Length.LengthUnit.INCHES);
	    Length l2 = new Length(6.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2);

	    assertEquals("12.0 INCHES", result.toString());
	}

	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2);

	    assertEquals("2.0 FEET", result.toString());
	}

	@Test
	public void testAddition_CrossUnit_InchPlusFeet() {
	    Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
	    Length l2 = new Length(1.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    assertEquals("24.0 INCHES", result.toString());
	}

	@Test
	public void testAddition_CrossUnit_YardPlusFeet() {
	    Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
	    Length l2 = new Length(3.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    assertEquals("2.0 YARDS", result.toString());
	}

	@Test
	public void testAddition_CrossUnit_CentimeterPlusInch() {
	    Length l1 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
	    Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2);

	    assertEquals("5.08 CENTIMETERS", result.toString());
	}

	@Test
	public void testAddition_Commutativity() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

	    Length result1 = l1.add(l2);
	    Length result2 = l2.add(l1);

	    assertTrue(
	        result1.convertTo(Length.LengthUnit.INCHES)
	               .equals(result2.convertTo(Length.LengthUnit.INCHES))
	    );
	}

	@Test
	public void testAddition_WithZero() {
	    Length l1 = new Length(5.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

	    Length result = l1.add(l2);

	    assertEquals("5.0 FEET", result.toString());
	}

	@Test
	public void testAddition_NegativeValues() {
	    Length l1 = new Length(5.0, Length.LengthUnit.FEET);
	    Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    assertEquals("3.0 FEET", result.toString());
	}

	@Test
	public void testAddition_NullSecondOperand() {
	    Length l1 = new Length(1.0, Length.LengthUnit.FEET);

	    assertThrows(IllegalArgumentException.class, () -> {
	        l1.add(null);
	    });
	}

	@Test
	public void testAddition_LargeValues() {
	    Length l1 = new Length(1e6, Length.LengthUnit.FEET);
	    Length l2 = new Length(1e6, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    assertEquals("2000000.0 FEET", result.toString());
	}

	@Test
	public void testAddition_SmallValues() {
	    Length l1 = new Length(0.001, Length.LengthUnit.FEET);
	    Length l2 = new Length(0.002, Length.LengthUnit.FEET);

	    Length result = l1.add(l2);

	    assertEquals("0.0 FEET", result.toString());
	}
}