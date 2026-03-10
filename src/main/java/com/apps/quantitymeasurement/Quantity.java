package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

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
    
    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = convertToBaseUnit();

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        converted = Math.round(converted * 100.0) / 100.0;

        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();

        double sum = base1 + base2;

        double converted = targetUnit.convertFromBaseUnit(sum);

        converted = Math.round(converted * 100.0) / 100.0;

        return new Quantity<>(converted, targetUnit);
    }
    
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }
    
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        double baseResult = baseValue1 - baseValue2;

        double convertedValue = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(convertedValue, targetUnit);
    }
    
    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        if (baseValue2 == 0)
            throw new ArithmeticException("Division by zero");

        return baseValue1 / baseValue2;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Quantity))
            return false;

        Quantity<?> that = (Quantity<?>) o;

        if (this.unit.getClass() != that.unit.getClass())
            return false;

        return Double.compare(
                this.convertToBaseUnit(),
                that.convertToBaseUnit()
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
    
    public double getValue() {
        return value;
    }
}