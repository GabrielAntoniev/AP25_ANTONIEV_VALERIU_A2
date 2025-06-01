package org.example.lab11.repository;

import org.example.lab11.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
//    @Override
//    public List<Country> findAll();
}
