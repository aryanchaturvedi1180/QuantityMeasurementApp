package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    
	 @Test
	    void testIMeasurableInterface_LengthUnitImplementation() {

	        LengthUnit unit = LengthUnit.FEET;

	        assertEquals(12.0, unit.convertToBaseUnit(1.0));
	        assertEquals(1.0, unit.convertFromBaseUnit(12.0));
	        assertEquals("FEET", unit.getUnitName());
	    }

	    @Test
	    void testIMeasurableInterface_WeightUnitImplementation() {

	        WeightUnit unit = WeightUnit.KILOGRAM;

	        assertEquals(1.0, unit.convertToBaseUnit(1.0));
	        assertEquals(1.0, unit.convertFromBaseUnit(1.0));
	        assertEquals("KILOGRAM", unit.getUnitName());
	    }

	    @Test
	    void testIMeasurableInterface_ConsistentBehavior() {

	        IMeasurable length = LengthUnit.FEET;
	        IMeasurable weight = WeightUnit.KILOGRAM;

	        assertTrue(length.convertToBaseUnit(1) > 0);
	        assertTrue(weight.convertToBaseUnit(1) > 0);
	    }
	    
	    @Test
	    void testGenericQuantity_LengthOperations_Equality() {

	        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

	        assertEquals(q1, q2);
	    }

	    @Test
	    void testGenericQuantity_WeightOperations_Equality() {

	        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

	        assertEquals(q1, q2);
	    }

	    @Test
	    void testGenericQuantity_LengthOperations_Conversion() {

	        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

	        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

	        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
	    }

	    @Test
	    void testGenericQuantity_WeightOperations_Conversion() {

	        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

	        Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);

	        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
	    }

	    @Test
	    void testGenericQuantity_LengthOperations_Addition() {

	        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
	        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

	        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

	        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
	    }

	    @Test
	    void testGenericQuantity_WeightOperations_Addition() {

	        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

	        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);

	        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
	    }
	    
	    @Test
	    void testCrossCategoryPrevention_LengthVsWeight() {

	        Quantity<LengthUnit> length =
	                new Quantity<>(1.0, LengthUnit.FEET);

	        Quantity<WeightUnit> weight =
	                new Quantity<>(1.0, WeightUnit.KILOGRAM);

	        assertNotEquals(length, weight);
	    }
	    
	    @Test
	    void testGenericQuantity_ConstructorValidation_NullUnit() {

	        assertThrows(IllegalArgumentException.class,
	                () -> new Quantity<>(1.0, null));
	    }

	    @Test
	    void testGenericQuantity_ConstructorValidation_InvalidValue() {

	        assertThrows(IllegalArgumentException.class,
	                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
	    }
	    
	    @Test
	    void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(1, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(12, LengthUnit.INCHES);

	        assertTrue(
	                QuantityMeasurementApp.demonstrateEquality(q1, q2)
	        );
	    }

	    @Test
	    void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {

	        Quantity<WeightUnit> q =
	                new Quantity<>(1, WeightUnit.KILOGRAM);

	        Quantity<WeightUnit> result =
	                QuantityMeasurementApp.demonstrateConversion(q, WeightUnit.GRAM);

	        assertEquals(new Quantity<>(1000, WeightUnit.GRAM), result);
	    }
	    
	    @Test
	    void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {

	        Quantity<WeightUnit> q1 =
	                new Quantity<>(1, WeightUnit.KILOGRAM);

	        Quantity<WeightUnit> q2 =
	                new Quantity<>(1000, WeightUnit.GRAM);

	        Quantity<WeightUnit> result =
	                QuantityMeasurementApp.demonstrateAddition(q1, q2, WeightUnit.KILOGRAM);

	        assertEquals(new Quantity<>(2, WeightUnit.KILOGRAM), result);
	    }
	    
	    @Test
	    void testImmutability_GenericQuantity() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(1, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                q1.convertTo(LengthUnit.INCHES);

	        assertNotSame(q1, q2);
	    }
}