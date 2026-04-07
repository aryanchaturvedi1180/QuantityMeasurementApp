# **QuantityMeasurementApp**

## Overview

This repository documents the daily progress of tasks completed during the BridgeLabz Training Program.

The project focuses on building a **Quantity Measurement Application using Java**, following **clean coding principles, Object-Oriented Design, and Test-Driven Development (TDD)**.

The application evolves step-by-step from basic equality checks to a **full Spring Boot application with database integration**.

---

## Repository Details

* **Repository Name:** QuantityMeasurementApp
* **Primary Language:** Java
* **IDE Used:** Eclipse
* **Version Control:** Git & GitHub
* **Development Approach:** Feature Branch Workflow

---

## Branch Information

| Branch Name                        | Purpose                                 |
| ---------------------------------- | --------------------------------------- |
| main                               | Contains final README and documentation |
| dev                                | Base development branch                 |
| feature/UC1-FeetEquality           | UC1: Feet Equality                      |
| feature/UC2-InchEquality           | UC2: Feet & Inches Equality             |
| feature/UC3-GenericLength          | UC3: Generic Quantity                   |
| feature/UC4-ExtendedUnitSupport    | UC4: Add new units                      |
| feature/UC5-UnitConversion         | UC5: Conversion logic                   |
| feature/UC6-Addition               | UC6: Addition of quantities             |
| feature/UC7-AdditionWithTargetUnit | UC7: Addition with result unit          |
| feature/UC8-RefactorUnits          | UC8: Move logic to enum                 |
| feature/UC9-WeightSupport          | UC9: Add weight                         |
| feature/UC10-GenericQuantity       | UC10: Generic class                     |
| feature/UC11-VolumeSupport         | UC11: Add volume                        |
| feature/UC12-ArithmeticOps         | UC12: Subtract & Divide                 |
| feature/UC13-RefactorDRY           | UC13: Remove duplication                |
| feature/UC14-TemperatureSupport    | UC14: Add temperature                   |
| feature/UC15-NTierArchitecture     | UC15: Layered architecture              |
| feature/UC16-JDBCPersistence       | UC16: Database integration              |
| feature/UC17-SpringBootMigration   | UC17: Spring Boot                       |

---

## 📂 Project Structure

```
QuantityMeasurementApp/
│
├── main/
│   └── README.md
│
├── dev/
│
├── feature/UC1-FeetEquality/
├── feature/UC2-InchEquality/
├── feature/UC3-GenericLength/
├── feature/UC4-ExtendedUnitSupport/
├── feature/UC5-UnitConversion/
├── feature/UC6-Addition/
├── feature/UC7-AdditionWithTargetUnit/
├── feature/UC8-RefactorUnits/
├── feature/UC9-WeightSupport/
├── feature/UC10-GenericQuantity/
├── feature/UC11-VolumeSupport/
├── feature/UC12-ArithmeticOps/
├── feature/UC13-RefactorDRY/
├── feature/UC14-TemperatureSupport/
├── feature/UC15-NTierArchitecture/
├── feature/UC16-JDBCPersistence/
└── feature/UC17-SpringBootMigration/
```

---

# Use Case Details

### UC1 – Feet Equality

* Compare two values in feet
* Override `equals()` and `hashCode()`
* Implement unit tests

---

### UC2 – Feet & Inches Equality

* Support feet and inches
* Compare values instead of references
* Identify code duplication

---

### UC3 – Generic Quantity Class

* Create a single `Quantity` class
* Use enum for unit handling
* Remove duplicate code (DRY)

---

### UC4 – Extended Unit Support

* Add new units like yard, cm
* No change in core logic
* Improve scalability

---

### UC5 – Unit Conversion

* Convert between units
* Use base unit approach
* Handle validation and precision

---

### UC6 – Addition of Quantities

* Add two quantities
* Convert to base unit before addition
* Maintain immutability

---

### UC7 – Addition with Target Unit

* Allow result in user-defined unit
* Use method overloading
* Improve flexibility

---

### UC8 – Refactor Unit Logic

* Move conversion logic to enum
* Reduce coupling
* Improve maintainability

---

### UC9 – Weight Support

* Add weight measurement
* Use same design structure
* Ensure type safety

---

### UC10 – Generic Quantity Class

* Introduce `Quantity<U>`
* Use interface for units
* Make system scalable

---

### UC11 – Volume Support

* Add volume measurement
* No change in existing code
* Follow Open-Closed Principle

---

### UC12 – Arithmetic Operations

* Add subtraction and division
* Return quantity and ratio
* Extend functionality

---

### UC13 – DRY Refactoring

* Remove repeated logic
* Create common helper methods
* Improve maintainability

---

### UC14 – Temperature Support

* Add temperature measurement
* Restrict invalid operations
* Use capability-based design

---

### UC15 – N-Tier Architecture

* Separate Controller, Service, Repository
* Improve structure and scalability
* Prepare for real-world systems

---

### UC16 – JDBC Persistence

* Add database support
* Use JDBC and parameterized queries
* Ensure security and performance

---

### UC17 – Spring Boot Migration

* Convert project to Spring Boot
* Use REST APIs and JPA
* Improve development and scalability

