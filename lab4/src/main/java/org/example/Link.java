package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Link{
    private Location location1;
    private Location location2;
    private boolean directMovement;
    private double timeCost;
    private double safeReachProbability;
}
