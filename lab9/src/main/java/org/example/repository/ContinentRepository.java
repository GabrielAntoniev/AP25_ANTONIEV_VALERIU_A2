package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.Database;
import org.example.LogUtils;
import org.example.entity.Continent;
import org.example.entity.Country;

import java.util.List;
import java.util.logging.Logger;

public class ContinentRepository extends AbstractRepository<Continent> {

    @Override
    public boolean exists(Continent continent) {
        return findByName(continent.getName()) != null;
    }

    public void create(Continent continent) {
        if (findByName(continent.getName()) == null) {
            long start = System.currentTimeMillis();
            em.getTransaction().begin();
            em.persist(continent);
            em.getTransaction().commit();
            long end = System.currentTimeMillis();
            logger.info("Persisted " + continent.getClass().getName() + " in " +
                    String.valueOf(end - start) + " ms.");
        }
    }

    public Continent findByName(String name) {
        long start = System.currentTimeMillis();
        Continent result = null;

        result = em.createNamedQuery("Continent.findByName", Continent.class)
                .setParameter("name", name)
                .getSingleResult();

        long end = System.currentTimeMillis();
        logger.info("Executed query Continent.findByName in " + String.valueOf(end - start) + " ms.");

        return result;
    }
}
