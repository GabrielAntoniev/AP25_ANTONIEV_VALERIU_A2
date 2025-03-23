package org.example;
import java.util.*;

public class Airport {
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
