package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;


public class QuantityMeasurementApp {

	public static void main(String[] args) {

		QuantityMeasurementDatabaseRepository repo =
                new QuantityMeasurementDatabaseRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(20, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(15, "FEET", "LENGTH");

        controller.performAddition(q1, q2);
        controller.performAddition(q1, q2);
        controller.performSubtraction(q1, q2);
        controller.performDivision(q1, q2);
        controller.performComparison(q1, q2);
        controller.performConversion(q1, "INCHES");
        controller.deleteAllMeasurements();
    }
}