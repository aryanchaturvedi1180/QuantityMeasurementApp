package com.apps.quantitymeasurement.service;

import org.springframework.stereotype.Service;

import com.apps.quantitymeasurement.dto.QuantityInputDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.ArithmeticNotSupportedException;
import com.apps.quantitymeasurement.exception.DivisionByZeroException;
import com.apps.quantitymeasurement.exception.InvalidMeasurementTypeException;
import com.apps.quantitymeasurement.exception.InvalidUnitException;
import com.apps.quantitymeasurement.exception.MeasurementMismatchException;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.units.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private final QuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	// common method

	private IMeasurable getUnit(String measurementType, String unit) {
		try {
			unit = unit.toUpperCase();

			return switch (measurementType.toUpperCase()) {
			case "LENGTH" -> LengthUnit.valueOf(unit);
			case "WEIGHT" -> WeightUnit.valueOf(unit);
			case "VOLUME" -> VolumeUnit.valueOf(unit);
			case "TEMPERATURE" -> TemperatureUnit.valueOf(unit);
			default -> throw new InvalidMeasurementTypeException("Invalid measurement type");
			};
		} catch (IllegalArgumentException e) {
			throw new InvalidUnitException("Invalid unit: " + unit);
		}
	}

	// compare

	@Override
	public QuantityMeasurementEntity compare(QuantityInputDTO input) {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

		var q1 = input.getThisQuantityDTO();
		var q2 = input.getThatQuantityDTO();

		if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
			throw new MeasurementMismatchException("Measurement types must be same");
		}

		IMeasurable unit1 = getUnit(q1.getMeasurementType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getMeasurementType(), q2.getUnit());

		double base1 = unit1.convertToBaseUnit(q1.getValue());
		double base2 = unit2.convertToBaseUnit(q2.getValue());

		boolean result = base1 == base2;

		setCommonFields(entity, input);
		entity.setOperation("COMPARE");
		entity.setResultString(String.valueOf(result));
		entity.setCreatedAt(LocalDateTime.now());

		return repository.save(entity);
	}

	// convert
	@Override
	public QuantityMeasurementEntity convert(QuantityInputDTO input) {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

		var from = input.getThisQuantityDTO();
		var to = input.getThatQuantityDTO(); // target unit

		if (to == null || to.getUnit() == null) {
			throw new InvalidUnitException("Target unit is required");
		}

		if (!from.getMeasurementType().equalsIgnoreCase(to.getMeasurementType())) {
			throw new MeasurementMismatchException("Measurement types must be same");
		}

		// Source unit
		IMeasurable fromUnit = getUnit(from.getMeasurementType(), from.getUnit());

		// Target unit
		IMeasurable toUnit = getUnit(to.getMeasurementType(), to.getUnit());

		// Convert to base
		double baseValue = fromUnit.convertToBaseUnit(from.getValue());

		// Convert to target
		double result = toUnit.convertFromBaseUnit(baseValue);

		// Set entity
		entity.setThisValue(from.getValue());
		entity.setThisUnit(from.getUnit());
		entity.setThisMeasurementType(from.getMeasurementType());

		entity.setResultValue(result);
		entity.setResultUnit(to.getUnit());
		entity.setOperation("CONVERT");
		entity.setCreatedAt(LocalDateTime.now());

		return repository.save(entity);
	}

	// add

	@Override
	public QuantityMeasurementEntity add(QuantityInputDTO input) {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

		var q1 = input.getThisQuantityDTO();
		var q2 = input.getThatQuantityDTO();

		IMeasurable unit1 = getUnit(q1.getMeasurementType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getMeasurementType(), q2.getUnit());

		if (!unit1.supportsArithmetic() || !unit2.supportsArithmetic()) {
			throw new ArithmeticNotSupportedException("Arithmetic not supported for this unit");
		}

		double base1 = unit1.convertToBaseUnit(q1.getValue());
		double base2 = unit2.convertToBaseUnit(q2.getValue());

		double resultBase = base1 + base2;

		double finalResult = unit1.convertFromBaseUnit(resultBase);

		setCommonFields(entity, input);
		entity.setResultValue(finalResult);
		entity.setResultUnit(q1.getUnit());
		entity.setOperation("ADD");
		entity.setCreatedAt(LocalDateTime.now());

		return repository.save(entity);
	}

	// subtract

	@Override
	public QuantityMeasurementEntity subtract(QuantityInputDTO input) {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

		var q1 = input.getThisQuantityDTO();
		var q2 = input.getThatQuantityDTO();

		IMeasurable unit1 = getUnit(q1.getMeasurementType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getMeasurementType(), q2.getUnit());

		if (!unit1.supportsArithmetic() || !unit2.supportsArithmetic()) {
			throw new ArithmeticNotSupportedException("Arithmetic not supported for this unit");
		}

		double base1 = unit1.convertToBaseUnit(q1.getValue());
		double base2 = unit2.convertToBaseUnit(q2.getValue());

		double resultBase = base1 - base2;

		double finalResult = unit1.convertFromBaseUnit(resultBase);

		setCommonFields(entity, input);
		entity.setResultValue(finalResult);
		entity.setResultUnit(q1.getUnit());
		entity.setOperation("SUBTRACT");
		entity.setCreatedAt(LocalDateTime.now());

		return repository.save(entity);
	}

	@Override
	public QuantityMeasurementEntity divide(QuantityInputDTO input) {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

		var q1 = input.getThisQuantityDTO();
		var q2 = input.getThatQuantityDTO();

		IMeasurable unit1 = getUnit(q1.getMeasurementType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getMeasurementType(), q2.getUnit());

		if (!unit1.supportsArithmetic() || !unit2.supportsArithmetic()) {
			throw new ArithmeticNotSupportedException("Arithmetic not supported for this unit");
		}

		double base1 = unit1.convertToBaseUnit(q1.getValue());
		double base2 = unit2.convertToBaseUnit(q2.getValue());

		if (base2 == 0) {
			throw new DivisionByZeroException("Cannot divide by zero");
		}

		double result = base1 / base2;

		setCommonFields(entity, input);
		entity.setResultValue(result);
		entity.setResultUnit("RATIO"); // division ka unit
		entity.setOperation("DIVIDE");
		entity.setCreatedAt(LocalDateTime.now());

		return repository.save(entity);
	}

	// history

	@Override
	public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
		return repository.findByOperation(operation.toUpperCase());
	}

	// count

	@Override
	public long getOperationCount(String operation) {
		return repository.countByOperationAndErrorFalse(operation.toUpperCase());
	}

	// common setter

	private void setCommonFields(QuantityMeasurementEntity entity, QuantityInputDTO input) {

		var q1 = input.getThisQuantityDTO();
		var q2 = input.getThatQuantityDTO();

		entity.setThisValue(q1.getValue());
		entity.setThisUnit(q1.getUnit());
		entity.setThisMeasurementType(q1.getMeasurementType());

		if (q2 != null) {
			entity.setThatValue(q2.getValue());
			entity.setThatUnit(q2.getUnit());
			entity.setThatMeasurementType(q2.getMeasurementType());
		}
	}
}