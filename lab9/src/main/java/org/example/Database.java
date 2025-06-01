package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Database {
    static String PU_NAME = "ExamplePU";
    private static EntityManagerFactory emf = null;

    private static void createEmf() {
        emf = Persistence.createEntityManagerFactory(PU_NAME);
    }

    public static EntityManager getEntityManager() {
        if(emf == null) {
            createEmf();
        }

        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
