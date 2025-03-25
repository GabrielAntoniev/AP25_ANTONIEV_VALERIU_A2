package org.example;

import java.util.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Bonus {

    private List<Runway> runways;
    private List<Flight> flights;
    private Map<Runway, List<Flight>> solution = new HashMap<>();

    public void findSolution() {
        for (Runway r : runways) {
            solution.put(r, new ArrayList<>());
        }

        flights.sort(Comparator.comparing(Flight::getArrival).thenComparing(Flight::getDeparture));
        List<Flight> copFlights = new ArrayList<>(flights);

        int minDif;
        int nrAssignedFlights = 0;

        boolean smthhappened = true;
        while (smthhappened) {
            smthhappened = false;
            minDif = 1_000_000;

            for (Runway r : runways){
                minDif = Math.min(minDif, solution.get(r).size());
            }
            for (int i = 0; i < copFlights.size(); i++){

                Flight f = copFlights.get(i);
                boolean foundSchedule = false;
                for (Runway r : runways) {
                    if (existsSpaceForFlight(f, r) && solution.get(r).size() == minDif) {
                        solution.get(r).add(f);
                        copFlights.remove(f);
                        foundSchedule = true;
                        break;
                    }
                }
                if (foundSchedule) {
                    nrAssignedFlights++;
                    smthhappened = true;
                }
            }
        }
        printSolution(nrAssignedFlights);
    }

    private boolean existsSpaceForFlight(Flight flight, Runway runway) {
        List<Flight> assignedFlights = solution.get(runway);
        if(assignedFlights.isEmpty()) return true;
        for (Flight f : assignedFlights) {
            if (flight.getDeparture().isBefore(f.getArrival()) && flight.getArrival().isAfter(f.getDeparture())) {
                return false;
            }
        }
        return true;
    }

    private void printSolution(int nrAssignedFlights) {

        System.out.println("Generated flights: " + flights.size());
        for(var f : flights) System.out.println(f);
        System.out.println("Nr. of runways: " + runways.size());

        System.out.println("Flight assignments:");
        for (Map.Entry<Runway, List<Flight>> entry : solution.entrySet()) {
            System.out.println("Runway " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Unassigned flights: " + (flights.size() - nrAssignedFlights));
        System.out.println("Assigned flights: " + nrAssignedFlights);
        System.out.println("Nr. of additional runways needed:" + (int)(Math.ceil((double) (flights.size() - nrAssignedFlights) /(double)(((double)flights.size()/(double)runways.size())))));
    }
}
