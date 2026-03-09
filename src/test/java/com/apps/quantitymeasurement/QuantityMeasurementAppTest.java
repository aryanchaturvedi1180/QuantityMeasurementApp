package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    
	private static final double EPSILON = 0.001;

    // ---------- EQUALITY TESTS ----------

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(2.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
        );
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue() {
        assertEquals(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_NullComparison() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                null
        );
    }

    @Test
    void testEquality_SameReference() {

        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(v, v);
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_ZeroValue() {
        assertEquals(
                new Quantity<>(0.0, VolumeUnit.LITRE),
                new Quantity<>(0.0, VolumeUnit.MILLILITRE)
        );
    }

    @Test
    void testEquality_NegativeVolume() {
        assertEquals(
                new Quantity<>(-1.0, VolumeUnit.LITRE),
                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)
        );
    }

    @Test
    void testEquality_LargeVolumeValue() {
        assertEquals(
                new Quantity<>(1000000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1000.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_SmallVolumeValue() {
        assertEquals(
                new Quantity<>(0.001, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.MILLILITRE)
        );
    }

    // ---------- CONVERSION TESTS ----------

    @Test
    void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testConversion_MillilitreToLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testConversion_SameUnit() {

        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testConversion_ZeroValue() {

        Quantity<VolumeUnit> result =
                new Quantity<>(0.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(0.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testConversion_NegativeValue() {

        Quantity<VolumeUnit> result =
                new Quantity<>(-1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    // ---------- ADDITION TESTS ----------

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(
                new Quantity<>(3.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_SameUnit_MillilitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));

        assertEquals(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(2000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testAddition_WithZero() {

        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_NegativeValues() {

        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(
                new Quantity<>(3.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_LargeValues() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1e6, VolumeUnit.LITRE)
                        .add(new Quantity<>(1e6, VolumeUnit.LITRE));

        assertEquals(
                new Quantity<>(2e6, VolumeUnit.LITRE),
                result
        );
    }
}