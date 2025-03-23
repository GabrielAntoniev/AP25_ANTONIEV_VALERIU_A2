package org.example;

abstract public class Aircraft implements Comparable<Aircraft>{
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
    }
}
