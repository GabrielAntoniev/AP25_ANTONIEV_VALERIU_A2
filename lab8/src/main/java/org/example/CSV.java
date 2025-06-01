package org.example;

import com.opencsv.CSVReader;
import org.example.dao.CityDAO;
import org.example.dao.ContinentDAO;
import org.example.dao.CountryDAO;
import org.example.model.City;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CSV {
    private Map<String, String> values;
    private final String path =
            Objects.requireNonNull(Main.class.getClassLoader().
                    getResource("orase.csv")).getPath();


    public List<City> loadCities() {
        List<City> cities = new ArrayList<>();
        var cityDao = new CityDAO();
        var continentDao = new ContinentDAO();
        var countryDao = new CountryDAO();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> lines = reader.readAll();

//            for(var line : lines) {
//                for(var i : line) {
//                    System.out.print(i + "@");
//                }
//                System.out.println();
//            }

            for (var line : lines) {

                if (continentDao.findByName(line[5]) == null) {
                    System.out.println(line[5]);
                    continentDao.create(line[5]);
                }

                if (countryDao.findByName(line[0]) == null) {
                    countryDao.create(line[0], line[4], continentDao.findByName(line[5]).getId());
                }

                var city = new City(
                        0,
                        line[1],
                        true,
                        Double.parseDouble(line[2]),
                        Double.parseDouble(line[3]),
                        countryDao.findByName(line[0]).getId()
                );
                cities.add(city);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
