package com.quantitymeasurement.repository;

import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

	List<QuantityMeasurementEntity> findByOperation(String operation);

	List<QuantityMeasurementEntity> findByOperationIgnoreCase(String operation);

	long countByOperation(String operation);
}