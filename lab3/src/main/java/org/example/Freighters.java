package org.example;

public class Freighters extends Aircraft implements PassengerCapable, CargoCapable{
    private int wingSpan;
    private double maximumPayload;

    public Freighters(){}

    public Freighters(String name, String model, String tailNumber, int wingSpan, double maximumPayload){
        super(name, model, tailNumber);
        this.wingSpan = wingSpan;
        this.maximumPayload = maximumPayload;
    }

    @Override
    public double getMaximumPayload() {
        return maximumPayload;
    }

    public void setMaximumPayload(double maximumPayload) {
        this.maximumPayload = maximumPayload;
    }

    public int getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
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
