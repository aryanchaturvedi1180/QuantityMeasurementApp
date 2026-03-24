package com.quantitymeasurement.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String operand1;
	private String operand2;
	private String operation;
	private String result;

	private LocalDateTime createdAt;

	public QuantityMeasurementEntity() {
	}

	public QuantityMeasurementEntity(String operand1, String operand2, String operation, String result) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operation = operation;
		this.result = result;
	}

	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	// getters & setters
	public Long getId() {
		return id;
	}

	public String getOperand1() {
		return operand1;
	}

	public void setOperand1(String operand1) {
		this.operand1 = operand1;
	}

	public String getOperand2() {
		return operand2;
	}

	public void setOperand2(String operand2) {
		this.operand2 = operand2;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}