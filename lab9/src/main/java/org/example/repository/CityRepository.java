package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.Database;
import org.example.LogUtils;
import org.example.entity.City;
import org.example.entity.Country;

import java.util.List;
import java.util.logging.Logger;

public class CityRepository extends AbstractRepository<City> {

    @Override
    public boolean exists(City city) {
        return findByName(city.getName()) != null;
    }

    public void create(City city) {
        if (findByName(city.getName()) == null) {
            long start = System.currentTimeMillis();
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
            long end = System.currentTimeMillis();
            logger.info("Persisted " + city.getClass().getName() + " in " +
                    String.valueOf(end - start) + " ms.");
        }
    }

    public City findByName(String name) {
        long start = System.currentTimeMillis();

        var result = em.createNamedQuery("City.findByName", City.class)
                .setParameter("name", name)
                .getSingleResult();

        long end = System.currentTimeMillis();
        logger.info("Executed query City.findByName in " + String.valueOf(end - start) + " ms.");

        return result;
    }
}