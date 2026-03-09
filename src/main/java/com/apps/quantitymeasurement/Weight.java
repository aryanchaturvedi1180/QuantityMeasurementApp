package com.apps.quantitymeasurement;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public boolean compare(Weight thatWeight) {
        return this.convertToBaseUnit() == thatWeight.convertToBaseUnit();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        return compare((Weight) o);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(base);

        return new Weight(converted, targetUnit);
    }


    public Weight add(Weight thatWeight) {

        if (thatWeight == null)
            throw new IllegalArgumentException("Weight cannot be null");

        return addAndConvert(thatWeight, this.unit);
    }

    public Weight add(Weight weight, WeightUnit targetUnit) {

        if (weight == null)
            throw new IllegalArgumentException("Weight cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return addAndConvert(weight, targetUnit);
    }

    private Weight addAndConvert(Weight weight, WeightUnit targetUnit) {

        double base1 = this.convertToBaseUnit();
        double base2 = weight.convertToBaseUnit();

        double sum = base1 + base2;

        double converted = targetUnit.convertFromBaseUnit(sum);

        converted = Math.round(converted * 100000.0) / 100000.0;

        return new Weight(converted, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}