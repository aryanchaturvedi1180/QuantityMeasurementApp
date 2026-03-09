package com.apps.quantitymeasurement;
public class QuantityMeasurementApp {
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
        
     public static void main(String[] args) {

         Feet firstValue = new Feet(5.0);
         Feet secondValue = new Feet(5.0);

         boolean result = firstValue.equals(secondValue);

         System.out.println("Are both values equal? " + result);
     }
}