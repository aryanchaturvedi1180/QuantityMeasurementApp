package com.apps.quantitymeasurement;

public class Length {
	private double value;
	private LengthUnit unit;

	public enum LengthUnit {
		FEET(12.0),
		INCHES(1.0),
		YARDS(36.0),
		CENTIMETERS(0.393701);
		
		private final double conversionFactor;
		
		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}
		
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	public Length(double value, LengthUnit unit) {
		this.value = value;
		this.unit = unit;
	}
	
	private double convertToBaseUnit() {
		return value * unit.getConversionFactor();
	}
	
	public boolean compare(Length thatLength) {
		return thatLength.convertToBaseUnit() == this.convertToBaseUnit();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		return compare((Length) o);
	}
	
    @Override
    public int hashCode() {
    	
        return Double.hashCode(convertToBaseUnit());
    }
	
	public Length convertTo(LengthUnit targetUnit) {
		if (targetUnit == null) {
			throw new IllegalArgumentException("TargetLength cannot be null.");
		}
		
		// find base length
		double baseLength = this.convertToBaseUnit();
		
		// convert to target length
		double conversionFactor = targetUnit.conversionFactor;
		double targetLength = baseLength / conversionFactor;
		
		// round off to 2 decimal places
		targetLength = (double) Math.round(targetLength * 100) / 100;
		
		return new Length(targetLength, targetUnit);
	}
	
	private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {

	    if (targetUnit == null) {
	        throw new IllegalArgumentException("Target unit cannot be null");
	    }

	    double convertedValue = lengthInInches / targetUnit.getConversionFactor();

	    // round to 2 decimal places
	    convertedValue = (double) Math.round(convertedValue * 100) / 100;

	    return convertedValue;
	}
	
	public Length add(Length thatLength) {

	    if (thatLength == null) {
	        throw new IllegalArgumentException("Length cannot be null");
	    }

	    double base1 = this.convertToBaseUnit();
	    double base2 = thatLength.convertToBaseUnit();

	    double sumInBase = base1 + base2;

	    double result = convertFromBaseToTargetUnit(sumInBase, this.unit);

	    return new Length(result, this.unit);
	}
	
	@Override
	public String toString() {
		return value + " " + unit.toString();
	}
	
	public static void main(String[] args) {
		
		Length length1 = new Length(1.0, LengthUnit.FEET);
		Length length2 = new Length(12, LengthUnit.INCHES);
		System.out.println("Are lengths equal? " + length1.equals(length2));
		
		
		Length length3 = new Length(1.0, LengthUnit.YARDS);
		Length length4 = new Length(36.0, LengthUnit.INCHES);
		System.out.println("Are lengths equal? " + length3.equals(length4));
		
		
		Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
		Length length6 = new Length(39.3701, LengthUnit.INCHES);
		System.out.println("Are lengths equal? " + length5.equals(length6));
	}
}