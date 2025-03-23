package org.example;
import java.time.LocalTime;

public class Flight {
    private LocalTime departure;
    private LocalTime arrival;

    public Flight(){}
    public Flight(LocalTime departure, LocalTime arrival){
        this.departure = departure;
        this.arrival = arrival;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public LocalTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }
}
