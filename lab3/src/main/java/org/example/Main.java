package org.example;
import java.time.LocalTime;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Airliner airliner = new Airliner("Boeing 747", "747-400", "N12345", 400, 64.4);
        Freighters freighter = new Freighters("Boeing 777F", "777-Freighter", "N67890", 65, 102.0);
        Drone drone = new Drone("DJI Phantom", "Phantom 4", "D001", 5.0, 30);

        Aircraft[] aircraftArray = {airliner, freighter, drone};

        for (Aircraft aircraft : aircraftArray) {
            aircraft.printAircraft();
            System.out.println("");
        }
        Airport airport = new Airport("Aeroport Iasi", 3);

        Flight[] flights ={
                new Flight(LocalTime.of(8, 0), LocalTime.of(9, 0)),
                new Flight(LocalTime.of(8, 30), LocalTime.of(9, 30)),
                new Flight(LocalTime.of(9, 15), LocalTime.of(10, 0)),
                new Flight(LocalTime.of(9, 45), LocalTime.of(10, 30)),
                new Flight(LocalTime.of(10, 15), LocalTime.of(11, 0))
        };

        SchedulingProblem problem = new SchedulingProblem(airport, flights);
        problem.printSolution();


        System.out.println("\n**************BONUS***************\n");
        Random rand = new Random();
        List<Runway> runways_bonus = new ArrayList<>();
        List<Flight> flights_bonus = new ArrayList<>();
        int nrFlights = 1 + (int) (Math.random() * 1_000_000) % 25;
        int nrRunways = 1 + (int) (Math.random() * 1_000_000) % nrFlights;

        for(int i = 0; i < nrRunways; i++){
            runways_bonus.add(new Runway(i));
        }
        for (int i = 1; i <= nrFlights; i++) {
            LocalTime departure = LocalTime.of(8 + (i % 4), (i * 10) % 60);
            LocalTime arrival = departure.plusMinutes((int) (Math.random() * 1_000_000) % 25 + (i * 6) % 15);
            flights_bonus.add(new Flight(departure, arrival));
        }
        var bonus = new Bonus(runways_bonus, flights_bonus, new HashMap<>());
        for (Runway r : runways_bonus) {
            bonus.getSolution().put(r, new ArrayList<>());
        }
        bonus.findSolution();
    }

}