package com.linkedin.flexibleconstructors.challenge;

public class ElectricCar extends Vehicle {
    private int batteryCapacity;  // in kWh
    private String chargingType;  // "FAST" or "STANDARD"

    public ElectricCar(String licensePlate, int year, int batteryCapacity) {
        // TODO: Add your prologue here
        //   1. Validate year
        if(year < 2010){
            throw new IllegalArgumentException("Year must be 2010 or later.");
        } 

        //   2. Validate batteryCapacity
        //   3. Initialize this.batteryCapacity

        if(batteryCapacity >= 200){
            throw new IllegalArgumentException("Battery capacity must be below 200 kWh.");
        } 

        if(batteryCapacity <= 40){
            throw new IllegalArgumentException("Battery capacity must be above 40 kWh.");
        } else {
            this.batteryCapacity = batteryCapacity;
            if(batteryCapacity >= 75){
                this.chargingType = "FAST";
            } else {
                this.chargingType = "STANDARD";
            }
        }




        
        //   4. Initialize this.chargingType

        // TODO: Call super constructor
        super(licensePlate, year);

        // TODO: Add your epilogue here
        //   - Print configuration message
        System.out.println("ElectricCar configuration: " + licensePlate + ", " + year + ", " + batteryCapacity + " kWh, " + chargingType);  

    }
}
