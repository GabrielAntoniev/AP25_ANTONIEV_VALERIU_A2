package org.example;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class Location implements Comparable{
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