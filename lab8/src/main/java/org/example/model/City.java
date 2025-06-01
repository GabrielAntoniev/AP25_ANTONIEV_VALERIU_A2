package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class City {
    private int id;
    private String name;
    private boolean capital;
    private double latitude;
    private double longitude;
    private int country;

    public double distance(City city) {
        double thisLat = Math.toRadians(this.latitude);
        double thisLon = Math.toRadians(this.longitude);
        double otherLat = Math.toRadians(city.getLatitude());
        double otherLon = Math.toRadians(city.getLongitude());

        double dlon = otherLon - thisLon;
        double dlat = otherLat - thisLat;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(thisLat) * Math.cos(otherLat)
                * Math.pow(Math.sin(dlon/2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        return (c * r);
    }
}
