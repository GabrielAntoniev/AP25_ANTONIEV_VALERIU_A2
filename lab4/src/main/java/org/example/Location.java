package org.example;

import lombok.*;
import com.github.javafaker.Faker;

import java.util.Random;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class Location implements Comparable<Object>{

    private String name;
    private Type type;

    public boolean isFriendly(){
        return this.type.equals(Type.FRIENDLY);
    }

    public boolean isEnemy(){
        return this.type.equals(Type.ENEMY);
    }

    public Location generateRandomLocation(Random random){
        this.name = new Faker().address().city();
        this.type = Type.values()[random.nextInt(3)];
        return this;
    }

    @Override
    public int compareTo(Object ceva){
        if(ceva == null)throw new NullPointerException("sdfgkldjfg");
        if(!(ceva instanceof Location))throw  new IllegalArgumentException("hasdhawdp");
        Location c = (Location)ceva;
        return this.name.compareTo(c.name);
    }
}