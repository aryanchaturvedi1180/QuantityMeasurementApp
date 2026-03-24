package com.quantitymeasurement;

import com.quantitymeasurement.units.*;

public class Quantity<U extends IMeasurable>{

    private final double value;
    private final U unit;

    private static final double EPSILON=0.000001;

    public Quantity(double value,U unit){

        if(unit==null)
            throw new IllegalArgumentException("Unit cannot be null");

        if(!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value=value;
        this.unit=unit;
    }

    public double getValue(){
        return value;
    }

    public U getUnit(){
        return unit;
    }

    private double toBaseUnit(){
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit){

        if(targetUnit==null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue=unit.convertToBaseUnit(value);
        double converted=targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted,targetUnit);
    }

    public Quantity<U> add(Quantity<U> other){

        validateOperands(other);

        unit.validateOperationSupport("ADD");

        double baseSum=this.toBaseUnit()+other.toBaseUnit();

        double result=unit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result,unit);
    }

    public Quantity<U> subtract(Quantity<U> other){

        validateOperands(other);

        unit.validateOperationSupport("SUBTRACT");

        double baseResult=this.toBaseUnit()-other.toBaseUnit();

        double result=unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result,unit);
    }

    public double divide(Quantity<U> other){

        validateOperands(other);

        unit.validateOperationSupport("DIVIDE");

        double divisor=other.toBaseUnit();

        if(Math.abs(divisor)<EPSILON)
            throw new ArithmeticException("Division by zero");

        return this.toBaseUnit()/divisor;
    }

    private void validateOperands(Quantity<U> other){

        if(other==null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if(this.unit.getClass()!=other.unit.getClass())
            throw new IllegalArgumentException(
                    "Cannot perform arithmetic between different measurement categories:  "
                    +this.unit.getClass().getSimpleName()+" and "
                    +other.unit.getClass().getSimpleName()
            );
    }

    @Override
    public boolean equals(Object obj){

        if(this==obj)
            return true;

        if(obj==null||getClass()!=obj.getClass())
            return false;

        Quantity<?> other=(Quantity<?>)obj;

        if(this.unit.getClass()!=other.unit.getClass())
            return false;

        return Math.abs(this.toBaseUnit()-other.toBaseUnit())<EPSILON;
    }

    @Override
    public int hashCode(){
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString(){
        return value+" "+unit;
    }
}