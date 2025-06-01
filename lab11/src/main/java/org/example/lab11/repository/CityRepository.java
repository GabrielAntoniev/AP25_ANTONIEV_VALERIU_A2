package org.example.lab11.repository;

import org.example.lab11.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);

    //City findById(Long id);
    //boolean findByName(String name);
//    @Override
//    public List<City> findAll();
//
//    @Override
//    public List<City> findAllById(Iterable<Long> ids);
//
//    @Override
//    public City getById(Long id);

    //List<City> findAllByName(String name);
}
