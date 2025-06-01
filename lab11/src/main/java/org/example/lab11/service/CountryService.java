package org.example.lab11.service;

import org.example.lab11.controller.CountryController;
import org.example.lab11.model.Country;
import org.example.lab11.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository){
        this.countryRepository = Objects.requireNonNull(countryRepository);
    }

    public List<Country> allCountries(){
        return countryRepository.findAll();
    }
}
