import java.time.LocalTime;
import java.util.*;

class Airport{

    private String name;
    private ArrayList<Runway> runways = new ArrayList<>();

    public Airport(){}
    public Airport(String name, int nrRunways){
        this.name  = name;
        for (int i=1; i <= nrRunways; i++) {
            runways.add(new Runway(i));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Runway> getRunways() {
        return runways;
    }

    public void setRunways(ArrayList<Runway> runways) {
        this.runways = runways;
    }
}

class Runway{

    private int id;

    public Runway(){}
    public Runway(int id){this.id = id;}

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}

class Flight{

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

class SchedulingProblem{

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

class hw_lab3{

    public static void main(String[] args){
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
    }
}