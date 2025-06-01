package org.example.lab11.service;

import org.example.lab11.model.City;
import org.example.lab11.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository){
        this.cityRepository = Objects.requireNonNull(cityRepository);
    }

    public List<City> allCities(){
        return cityRepository.findAll();
    }

    public String addCity(City city){
        if(cityRepository.findByName(city.getName())==null &&city.getCountry().getId() != null){
            cityRepository.save(city);
            return "ok";
        }
        return "buba";
    }

    public String updateCity(Long id, City city){
        if (cityRepository.findById(id).isEmpty()) return "buba";
        cityRepository.save(city);
        return "ok";
    }

    public String deleteCity(Long id){
        if (cityRepository.findById(id).isEmpty()) return "buba";
        cityRepository.deleteById(id);
        return "ok";
    }
}
