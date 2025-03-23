package org.example;

public class Airliner extends Aircraft implements PassengerCapable, CargoCapable{
    private int seatCount;
    private double wingSpan;

    public Airliner(){}

    public Airliner(String name, String model, String tailNumber, int seatCount, double wingSpan){
        super(name, model, tailNumber);
        this.wingSpan = wingSpan;
        this.seatCount = seatCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    @Override
    public boolean hasBusinessClassSeats() {
        return true;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(double wingSpan) {
        this.wingSpan = wingSpan;
    }

    @Override
    public double getMaximumPayload() {
        return 0;
    }
}
