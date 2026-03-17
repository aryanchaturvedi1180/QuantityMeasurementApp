package com.quantitymeasurement;
//package com.quantitymeasurement;

//
//import com.quantitymeasurement.controller.QuantityMeasurementController;
//import com.quantitymeasurement.dto.QuantityDTO;
//import com.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
//import com.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
//import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;
//
//
//public class QuantityMeasurementApp {
//
//	public static void main(String[] args) {
//
//		QuantityMeasurementDatabaseRepository repo =
//                new QuantityMeasurementDatabaseRepository();
//
//        QuantityMeasurementServiceImpl service =
//                new QuantityMeasurementServiceImpl(repo);
//
//        QuantityMeasurementController controller =
//                new QuantityMeasurementController(service);
//
//        QuantityDTO q1 = new QuantityDTO(20, "FEET", "LENGTH");
//        QuantityDTO q2 = new QuantityDTO(15, "FEET", "LENGTH");
//
//        controller.performAddition(q1, q2);
//        controller.performAddition(q1, q2);
//        controller.performSubtraction(q1, q2);
//        controller.performDivision(q1, q2);
//        controller.performComparison(q1, q2);
//        controller.performConversion(q1, "INCHES");
//        controller.deleteAllMeasurements();
//    }
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementApp {
	public static void main(String[] args) {
		SpringApplication.run(QuantityMeasurementApp.class, args);
	}
}