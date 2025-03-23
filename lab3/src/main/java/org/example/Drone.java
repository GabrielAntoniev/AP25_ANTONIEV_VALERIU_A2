package org.example;

public class Drone extends Aircraft implements PassengerCapable, CargoCapable{
    private double maximumPayload;
    private int batteryLife;

    public Drone(String name, String model, String tailNumber, double maximumPayload, int batteryLife){
        super(name, model, tailNumber);
        this.maximumPayload = maximumPayload;
        this.batteryLife = batteryLife;
    }

    @Override
    public double getMaximumPayload() {
        return maximumPayload;
    }

    public void setMaximumPayload(double maximumPayload) {
        this.maximumPayload = maximumPayload;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    @Override
    public int getSeatCount() {
        return 0;
    }

    @Override
    public boolean hasBusinessClassSeats() {
        return false;
    }
}
