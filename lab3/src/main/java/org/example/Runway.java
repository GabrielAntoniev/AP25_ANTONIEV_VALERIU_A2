package org.example;

import lombok.ToString;

@ToString
public class Runway {
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
