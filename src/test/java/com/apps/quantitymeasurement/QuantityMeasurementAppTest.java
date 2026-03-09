package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    
	private static final double EPSILON = 0.0001;

    // ---------- EQUALITY TESTS ----------

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(2.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        assertEquals(new Weight(1000.0, WeightUnit.GRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }

    @Test
    void testEquality_NullComparison() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        assertFalse(weight.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(weight.equals(weight));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1.0, null));
    }

    @Test
    void testEquality_TransitiveProperty() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);
        Weight c = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_ZeroValue() {
        assertEquals(new Weight(0.0, WeightUnit.KILOGRAM),
                new Weight(0.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_NegativeWeight() {
        assertEquals(new Weight(-1.0, WeightUnit.KILOGRAM),
                new Weight(-1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_LargeWeightValue() {
        assertEquals(new Weight(1000000.0, WeightUnit.GRAM),
                new Weight(1000.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_SmallWeightValue() {
        assertEquals(new Weight(0.001, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.GRAM));
    }

    // ---------- CONVERSION TESTS ----------

    @Test
    void testConversion_SameUnit() {

        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testConversion_ZeroValue() {

        Weight result = new Weight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(new Weight(0.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_NegativeValue() {

        Weight result = new Weight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(new Weight(-1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_RoundTrip() {

        Weight original = new Weight(1.5, WeightUnit.KILOGRAM);

        Weight result = original
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(original, result);
    }

    // ---------- ADDITION TESTS ----------

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {

        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM));

        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {

        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {

        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .add(new Weight(1.0, WeightUnit.KILOGRAM));

        assertEquals(new Weight(4.40924, WeightUnit.POUND), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Kilogram() {

        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(new Weight(2000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testAddition_Commutativity() {

        Weight a = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        Weight b = new Weight(1000.0, WeightUnit.GRAM)
                .add(new Weight(1.0, WeightUnit.KILOGRAM));

        assertTrue(a.equals(b));
    }

    @Test
    void testAddition_WithZero() {

        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(0.0, WeightUnit.GRAM));

        assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_NegativeValues() {

        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(-2000.0, WeightUnit.GRAM));

        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_LargeValues() {

        Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
                .add(new Weight(1e6, WeightUnit.KILOGRAM));

        assertEquals(new Weight(2e6, WeightUnit.KILOGRAM), result);
    }
}