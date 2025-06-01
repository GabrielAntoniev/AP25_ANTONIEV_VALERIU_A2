package org.example;

import org.example.entity.*;
import org.example.repository.*;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        homework();
    }

    static void homework() {
        Logger logger = LogUtils.getLogger();

        try (var continentRepository = new ContinentRepository();
             var countryRepository = new CountryRepository();
             var cityRepository = new CityRepository();){

            Continent europe = new Continent("Europe");
            Continent asia = new Continent("Asia");
            Continent africa = new Continent("Africa");
            continentRepository.create(europe);
            continentRepository.create(africa);
            continentRepository.create(asia);

            var continentToFind = continentRepository.findByName(europe.getName());
            logger.info("Find continent by name: " + continentToFind.getName());

            Country belgium = new Country("Belgium", "BE", europe);
            countryRepository.create(belgium);

            Country countryToFind = countryRepository.findByName(belgium.getName());
            logger.info("Find country by name: " + countryToFind.getName());

        } catch (RuntimeException e) {
            logger.severe(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
