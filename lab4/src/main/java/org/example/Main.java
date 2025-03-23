package org.example;

import lombok.*;
import java.util.stream.Collectors;
import java.util.*;

enum Type{
    friendly, neutral, enemy;
}

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
class Location implements Comparable{
    private String name;
    private Type type;

    public boolean isFriendly(){
        return this.type.equals(Type.friendly);
    }

    public boolean isEnemy(){
        return this.type.equals(Type.enemy);
    }

    @Override
    public int compareTo(Object ceva){
        if(ceva == null)throw new NullPointerException("sdfgkldjfg");
        if(!(ceva instanceof Location))throw  new IllegalArgumentException("hasdhawdp");
        Location c = (Location)ceva;
        return this.name.compareTo(c.name);
    }
}

class Main{
    public static void main(String[] args){

        int nrLocations = 10;
        List<Location> locationList = new ArrayList<>();
        for(int i = 0; i < nrLocations; i++){
            locationList.add(new Location("point" + i, Type.values()[i%3]));
            //System.out.println(locationList[i]);
        }

        Set<Location> locationTreeSet = locationList.stream()
                .filter(loc -> loc.isFriendly())
                .collect(Collectors.toCollection(TreeSet::new));

        List<Location> locationLinkedList = locationList.stream()
                .filter(loc -> loc.isEnemy())
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        for(var p : locationTreeSet){
            System.out.println(p);
        }

        for(var p : locationLinkedList){
            System.out.println(p);
        }
    }
}