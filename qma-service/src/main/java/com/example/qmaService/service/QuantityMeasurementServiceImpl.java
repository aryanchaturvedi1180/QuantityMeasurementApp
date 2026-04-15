package com.example.qmaService.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.qmaService.dto.QuantityDTO;
import com.example.qmaService.dto.QuantityInputDTO;
import com.example.qmaService.entity.QuantityMeasurementEntity;
import com.example.qmaService.exception.*;
import com.example.qmaService.repository.QuantityMeasurementRepository;
import com.example.qmaService.security.JwtUtil;
import com.example.qmaService.units.IMeasurable;
import com.example.qmaService.units.LengthUnit;
import com.example.qmaService.units.TemperatureUnit;
import com.example.qmaService.units.VolumeUnit;
import com.example.qmaService.units.WeightUnit;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private JwtUtil jwtUtil;
	
	private String getLoggedInEmail() {
//		System.out.println(SecurityContextHolder.getContext().getAuthentication()+"Hello");
	    var auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth == null || auth.getPrincipal() == null) {
	        return null;
	    }
	    System.out.println(auth.getPrincipal().toString());
	    return auth.getPrincipal().toString();
	}
	
	
    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
        this.repository = repository;
        
    }

    //common method

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

    //compare

    @Override
    public QuantityMeasurementEntity compare(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        QuantityDTO q1 = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();
        
        if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
            throw new MeasurementMismatchException("Measurement types must be same");
        }

        IMeasurable unit1 = getUnit(q1.getMeasurementType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getMeasurementType(), q2.getUnit());

        double base1 = unit1.convertToBaseUnit(q1.getValue());
        double base2 = unit2.convertToBaseUnit(q2.getValue());

        boolean result = base1 == base2;

        setCommonFields(entity, input);
        String email = getLoggedInEmail();

        if (email != null) {
            entity.setEmail(email);
        }
        entity.setOperation("COMPARE");
        entity.setResultValue(result ? 1.0 : 0.0);
        entity.setResultUnit("BOOLEAN");
        

        return repository.save(entity);
    }
    
    @Override
    public QuantityMeasurementEntity convert(QuantityInputDTO input) {


        String key = "CONVERT:" + input.getThisQuantityDTO().getValue()
                + ":" + input.getThisQuantityDTO().getUnit()
                + ":" + input.getThatQuantityDTO().getUnit();

        //  CACHE HIT
        Object cached = redisTemplate.opsForValue().get(key);
        if (cached != null) {
            return (QuantityMeasurementEntity) cached;
        }

        //  ORIGINAL LOGIC
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        QuantityDTO from = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();

        IMeasurable fromUnit = getUnit(from.getMeasurementType(), from.getUnit());
        IMeasurable toUnit = getUnit(q2.getMeasurementType(), q2.getUnit());

        double baseValue = fromUnit.convertToBaseUnit(from.getValue());
        double result = toUnit.convertFromBaseUnit(baseValue);

        String email = getLoggedInEmail();

        if (email != null) {
            entity.setEmail(email);
        }
        entity.setThisValue(from.getValue());
        entity.setThisUnit(from.getUnit());
        entity.setThisMeasurementType(from.getMeasurementType());
        entity.setResultValue(result);
        entity.setResultUnit(q2.getUnit());
        entity.setOperation("CONVERT");

        QuantityMeasurementEntity saved = repository.save(entity);

        //  SAVE TO CACHE
        redisTemplate.opsForValue().set(key, saved.getResultValue(), 10, TimeUnit.MINUTES);

        return saved;
    }

    //add

    @Override
    public QuantityMeasurementEntity add(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        QuantityDTO q1 = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();

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
        String email = getLoggedInEmail();

        System.out.println("FILTER AUTH SET: " + email);
        entity.setEmail(email);
        
        entity.setResultValue(finalResult);
        entity.setResultUnit(q1.getUnit());
        entity.setOperation("ADD");
        

        return repository.save(entity);
    }
    
    //subtract
    
    @Override
    public QuantityMeasurementEntity subtract(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        QuantityDTO q1 = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();

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
        String email = getLoggedInEmail();

        if (email != null) {
            entity.setEmail(email);
        }
        entity.setResultValue(finalResult);
        entity.setResultUnit(q1.getUnit());
        entity.setOperation("SUBTRACT");
        

        return repository.save(entity);
    }
    
    @Override
    public QuantityMeasurementEntity divide(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        QuantityDTO q1 = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();

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
        String email = getLoggedInEmail();

        if (email != null) {
            entity.setEmail(email);
        }
        entity.setResultValue(result);
        entity.setResultUnit("RATIO"); // division ka unit
        entity.setOperation("DIVIDE");
        

        return repository.save(entity);
    }

    //history

//    @Override
//    public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
//        return repository.findByOperation(operation.toUpperCase());
//    }
    
    @Override
    public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
        String email = getLoggedInEmail();
        System.out.println("FILTER AUTH SET: " + email);
        return repository.findByOperationAndEmail(operation.toUpperCase(), email);
    }

    //count

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperation(operation.toUpperCase());
    }

    //common setter

    private void setCommonFields(QuantityMeasurementEntity entity, QuantityInputDTO input) {

        var q1 = input.getThisQuantityDTO();
        var q2 = input.getThatQuantityDTO();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnit());
        entity.setThisMeasurementType(q1.getMeasurementType());
        String email = getLoggedInEmail();

//        if (email != null) {
//            entity.setEmail(email);
//        }
        if (q2 != null) {
            entity.setThatValue(q2.getValue());
            entity.setThatUnit(q2.getUnit());
            entity.setThatMeasurementType(q2.getMeasurementType());
        }
    }

}