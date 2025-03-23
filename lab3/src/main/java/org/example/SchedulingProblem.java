package org.example;
import java.time.LocalTime;
import java.util.*;

public class SchedulingProblem {
    private Airport airport;
    private Flight[] flights;

    public SchedulingProblem(){}
    public SchedulingProblem(Airport airport, Flight[] flights){
        this.airport = airport;
        this.flights = flights;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Flight[] getFlights() {
        return flights;
    }

    public void setFlights(Flight[] flights) {
        this.flights = flights;
    }

    public Map<Flight, Runway> planningRunways(){
        List<Runway> runways =airport.getRunways();
        Map<Flight, Runway> flightAssignments = new HashMap<>();
        Map<Runway, LocalTime> runwayAvailability = new HashMap<>();

        for(Runway runway : runways){
            runwayAvailability.put(runway, LocalTime.of(0, 0));
        }

        for(int i=0; i < flights.length;i++) {
            Flight flight = flights[i];
            for(int j=0; j < runways.size();j++) {
                Runway runway = runways.get(j);
                LocalTime lastFlightEnd = runwayAvailability.get(runway);

                if(lastFlightEnd.isBefore(flight.getDeparture())){
                    flightAssignments.put(flight, runway);
                    runwayAvailability.put(runway, flight.getArrival());
                    break;
                }
            }
        }
        return flightAssignments;
    }

    public void printSolution(){
        Map<Flight, Runway> assignments = planningRunways();
        for(Flight flight : flights){
            if(assignments.containsKey(flight)){
                Runway runway = assignments.get(flight);
                System.out.println("departure : " + flight.getDeparture() +" arrival : " + flight.getArrival() +" -> runway : " + runway.getId());
            }
            else{
                System.out.println("Zborul care incepe la" + flight.getDeparture() + " nu a putut fi planificat");
            }
        }
    }
}
