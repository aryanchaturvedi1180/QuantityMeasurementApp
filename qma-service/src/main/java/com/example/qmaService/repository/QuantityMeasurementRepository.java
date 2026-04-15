package com.example.qmaService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.qmaService.entity.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

	List<QuantityMeasurementEntity> findByOperationAndEmail(String operation, String email);

    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);

    long countByOperation(String operation);

}
