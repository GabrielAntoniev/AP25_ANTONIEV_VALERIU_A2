package org.example;

import org.example.dao.CityDAO;
import org.example.dao.ContinentDAO;
import org.example.dao.CountryDAO;
import org.example.model.City;
import org.example.model.Continent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //compulsory();
        homework();
    }

    static void compulsory() {
        try {
            var continents = new ContinentDAO();
            if(continents.findByName("Europe").getName() == null) {
                continents.create("Europe");
            }

            var countries = new CountryDAO();
            Continent continent = continents.findByName("Europe");
            countries.create("Romania", "RO", continent.getId());
            countries.create("Ukraine", "UA", continent.getId());
            //Database.getConnection().commit();

            try (PreparedStatement pstmt = Database.getConnection().prepareStatement(
                    "select name from countries where continent=?")) {
                pstmt.setInt(1, continent.getId());
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }

            Database.getConnection().close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void homework() {
        var loader = new CSV();
        var cityDao = new CityDAO();
        cityDao.deleteAll();
        loader.loadCities().forEach(city -> {
            try {
                cityDao.create(city);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            City Amsterdam = cityDao.findByName("Amsterdam");
            City Jakarta = cityDao.findByName("Jakarta");
            City Bucharest = cityDao.findByName("Bucharest");
            System.out.println("Distance between " + Amsterdam.getName() + " and " + Jakarta.getName() + " is " + Amsterdam.distance(Jakarta) + " km");
            System.out.println("Distance between " + Amsterdam.getName() + " and " + Bucharest.getName() + " is " + Amsterdam.distance(Bucharest) + " km");
            System.out.println("Distance between " + Bucharest.getName() + " and " + Jakarta.getName() + " is " + Bucharest.distance(Jakarta) + " km");

            Database.closeConnectionPool();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}