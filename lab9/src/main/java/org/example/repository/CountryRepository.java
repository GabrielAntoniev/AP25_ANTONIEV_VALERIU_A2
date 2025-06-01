package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.Database;
import org.example.LogUtils;
import org.example.entity.Continent;
import org.example.entity.Country;

import java.util.List;
import java.util.logging.Logger;

public class CountryRepository extends AbstractRepository<Country> {

    @Override
    public boolean exists(Country country) {
        return findByName(country.getName()) != null;
    }

    public void create(Country country) {
        if (findByName(country.getName()) == null) {
            long start = System.currentTimeMillis();
            em.getTransaction().begin();
            em.persist(country);
            em.getTransaction().commit();
            long end = System.currentTimeMillis();
            logger.info("Persisted " + country.getClass().getName() + " in " +
                    String.valueOf(end - start) + " ms.");
        }
    }

    public Country findByName(String name) {
        long start = System.currentTimeMillis();

        var result =  em.createNamedQuery("Country.findByName", Country.class)
                .setParameter("name", name)
                .getSingleResult();

        long end = System.currentTimeMillis();
        logger.info("Executed query Country.findByName in " + String.valueOf(end - start) + " ms.");

        return result;
    }

}
