interface PassengerCapable {
    int getSeatCount();
    boolean hasBusinessClassSeats();
}
//In CargoCapable.java
interface CargoCapable {
    double getMaximumPayload();
}

abstract class Aircraft implements Comparable<Aircraft>{

    protected String name;
    protected String model;
    protected String tailNumber;

    public Aircraft(){}

    public Aircraft(String name, String model, String tailNumber) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name= name;
    }

    public String getModel(){
        return this.model;
    }

    public void setModel(String model){
        this.model= model;
    }
    public String getTailNumber(){
        return this.tailNumber;
    }

    public void setTailNumber(String tailNumber){
        this.tailNumber= tailNumber;
    }

    public String getType(){
        return this.getClass().toString();
    }

    public void printAircraft(){
        System.out.println(this);
        System.out.println(this.getType());

        //if (aircraft instanceof PassengerCapable) {
        PassengerCapable passengerAircraft = (PassengerCapable)this;
        System.out.println("Seat Count: " + passengerAircraft.getSeatCount());
        System.out.println("Has Business Class Seats: " + passengerAircraft.hasBusinessClassSeats());
        //}

        //if (aircraft instanceof CargoCapable) {
        CargoCapable cargoAircraft = (CargoCapable)this;
        System.out.println("Maximum Payload: " + cargoAircraft.getMaximumPayload() + " tons");
    }

    @Override
    public String toString(){
        return "{\"Aircraft\":{\"name\":\""+name+"\",\"model\":\""+model+"\",\"tailnumber\":\""+tailNumber+"\"}}";
    }

    @Override
    public int compareTo(Aircraft other) {
        if(other.getName()==null) return 0;
        return this.name.compareTo(other.name);
        //not safe, check if name is null before
        }
    }

 class Airliner extends Aircraft implements PassengerCapable, CargoCapable {

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

 class Freighters extends Aircraft implements PassengerCapable, CargoCapable {

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

 class Drone extends Aircraft implements PassengerCapable, CargoCapable {

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

 class compulsory_lab3{

     public static void main(String[] args) {
         Airliner airliner = new Airliner("Boeing 747", "747-400", "N12345", 400, 64.4);
         Freighters freighter = new Freighters("Boeing 777F", "777-Freighter", "N67890", 65, 102.0);
         Drone drone = new Drone("DJI Phantom", "Phantom 4", "D001", 5.0, 30);

         Aircraft[] aircraftArray = {airliner, freighter, drone};

         for (Aircraft aircraft : aircraftArray) {
             aircraft.printAircraft();
             System.out.println("");
         }
     }
 }






