package com.quantitymeasurement.units;

import java.util.function.Function;
public enum TemperatureUnit implements IMeasurable {

	CELSIUS(false), FAHRENHEIT(true);

	private final boolean isFahrenheit;

	private final Function<Double, Double> conversionValue;

	SupportsArithmetic supportsArithmetic = () -> false;

	TemperatureUnit(boolean isFahrenheit) {

		this.isFahrenheit = isFahrenheit;

		if (isFahrenheit) {
			conversionValue = (f) -> (f - 32) * 5 / 9;
		} 
		else {
			conversionValue = (c) -> c;
		}
	}

	@Override
	public String getUnitName() {
		return this.name();
	}

	@Override
	public double getConversionFactor() {
		return 1.0;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return conversionValue.apply(value);
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {

		if (isFahrenheit)
			return (baseValue * 9 / 5) + 32;

		return baseValue;
	}

	public double convertTo(double value, TemperatureUnit targetUnit) {

		double base = convertToBaseUnit(value);
		return targetUnit.convertFromBaseUnit(base);
	}

	@Override
	public boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	@Override
	public void validateOperationSupport(String operation) {

		if (!supportsArithmetic()) {
			throw new UnsupportedOperationException("Temperature does not support " + operation + " operations");
		}
	}
}