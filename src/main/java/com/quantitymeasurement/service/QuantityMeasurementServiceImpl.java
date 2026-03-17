package com.quantitymeasurement.service;

import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl {

	private final QuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {

		double op1 = entity.getOperand1();
		double op2 = entity.getOperand2();
		String operation = entity.getOperation();

		double result = 0;

		// 🔹 Business Logic
		switch (operation.toLowerCase()) {
		case "add":
			result = op1 + op2;
			break;

		case "subtract":
			result = op1 - op2;
			break;

		case "multiply":
			result = op1 * op2;
			break;

		case "divide":
			if (op2 == 0) {
				throw new ArithmeticException("Cannot divide by zero");
			}
			result = op1 / op2;
			break;

		default:
			throw new IllegalArgumentException("Invalid operation: " + operation);
		}

		// Set result in entity
		entity.setResult(result);
		return repository.save(entity);
	}

	public List<QuantityMeasurementEntity> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(Long id) {
	    if (!repository.existsById(id)) {
	        throw new RuntimeException("Measurement not found with id: " + id);
	    }
	    repository.deleteById(id);
	}

	public void deleteAllMeasurements() {
		repository.deleteAll();
	}
}