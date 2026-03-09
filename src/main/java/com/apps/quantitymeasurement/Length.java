package com.apps.quantitymeasurement;

public class Length {

    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
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

    public boolean compare(Length thatLength) {
        return thatLength.convertToBaseUnit() == this.convertToBaseUnit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return compare((Length) o);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        double baseLength = convertToBaseUnit();

        double converted = targetUnit.convertFromBaseUnit(baseLength);

        converted = (double) Math.round(converted * 100) / 100;

        return new Length(converted, targetUnit);
    }

    
    public Length add(Length thatLength) {

        if (thatLength == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        return addAndConvert(thatLength, this.unit);
    }

    
    public Length add(Length length, LengthUnit targetUnit) {

        if (length == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        return addAndConvert(length, targetUnit);
    }

    private Length addAndConvert(Length length, LengthUnit targetUnit) {

        if (!Double.isFinite(this.value) || !Double.isFinite(length.value)) {
            throw new IllegalArgumentException("Length values must be finite numbers");
        }

        double base1 = this.convertToBaseUnit();
        double base2 = length.convertToBaseUnit();

        double sum = base1 + base2;

        double converted = targetUnit.convertFromBaseUnit(sum);

        converted = (double) Math.round(converted * 100) / 100;

        return new Length(converted, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}