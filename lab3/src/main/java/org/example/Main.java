package org.example;
import java.time.LocalTime;
import java.util.*;

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


        Random rand = new Random();
        List<Runway> runways = Arrays.asList(new Runway(1), new Runway(2));
        List<Flight> flights2 = new ArrayList<>();

        for (int i = 0; i < 21; i++) { // Generate random flights
            LocalTime dep = LocalTime.of(rand.nextInt(24), rand.nextInt(60));
            LocalTime arr = dep.plusMinutes(rand.nextInt(60) + 30);
            flights2.add(new Flight(dep, arr));
        }

        BonusLab4 scheduler = new BonusLab4(runways, flights2);
        scheduler.scheduleFlights();
        scheduler.printSchedule();
    }

}