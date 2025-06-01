package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.Database;
import org.example.LogUtils;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractRepository<T> implements AutoCloseable{
    protected final Logger logger = LogUtils.getLogger();
    protected EntityManager em = Database.getEntityManager();

    public void create(T entity) {
        if (!exists(entity)) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            logger.info("Saved entity: " + entity);
        } else {
            logger.info("Entity already exists: " + entity);
        }
    }

    public abstract boolean exists(T entity);
    public abstract T findByName(String name);

    @Override
    public void close() {
        em.close();
    }
}
