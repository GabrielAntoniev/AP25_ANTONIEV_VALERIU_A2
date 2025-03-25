package org.example;
import lombok.ToString;

import java.time.LocalTime;
import java.util.Collections;

@ToString
public class Flight implements Comparable<Flight>{
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

    @Override
    public int compareTo(Flight ceva) {
        if(ceva == null)throw new NullPointerException("obiect gol, nu pointeaza la nimic");
        return this.arrival.compareTo(((Flight) ceva).getArrival());
    }
}
