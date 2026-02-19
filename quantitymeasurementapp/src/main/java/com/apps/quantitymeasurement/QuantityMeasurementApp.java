package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
	// Inner class to represent Feet measurement
	public static class Feet {
		private final double value;

		public Feet(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			Feet other = (Feet) obj;
			return Double.compare(this.value, other.value) == 0;
		}

		@Override
		public int hashCode() {
			return Double.hashCode(value);
		}
	}

	// Inner class to represent Feet measurement
	public static class Inches {
		private final double value;

		public Inches(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			Inches other = (Inches) obj;
			return Double.compare(this.value, other.value) == 0;
		}

		@Override
		public int hashCode() {
			return Double.hashCode(value);
		}
	}

	// Define a static method to demonstrate Feet eqaulity check
	public static boolean demonstrateFeetEquality(double value1, double value2) {
		Feet f1 = new Feet(value1);
		Feet f2 = new Feet(value2);
		return f1.equals(f2);
	}

	// Define a static method to demonstrate Inches eqaulity check
	public static boolean demonstrateInchesEquality(double value1, double value2) {
		Inches i1 = new Inches(value1);
		Inches i2 = new Inches(value2);
		return i1.equals(i2);
	}

	public static void main(String[] args) {

		System.out.println("Feet Equal: " + demonstrateFeetEquality(1.0, 1.0));
		System.out.println("Inches Equal: " + demonstrateInchesEquality(1.0, 1.0));
	}

}