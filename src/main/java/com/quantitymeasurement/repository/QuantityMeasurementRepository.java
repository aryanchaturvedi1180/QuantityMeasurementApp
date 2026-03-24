package com.quantitymeasurement.repository;

import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
	
	List<QuantityMeasurementEntity> findByOperation(String operation);

    List<QuantityMeasurementEntity> findByOperationIgnoreCase(String operation);

    long countByOperation(String operation);
	
}