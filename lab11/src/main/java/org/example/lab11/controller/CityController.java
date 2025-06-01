package org.example.lab11.controller;

import org.example.lab11.model.City;
import org.example.lab11.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> allCities(){
        return ResponseEntity.ok(cityService.allCities());
    }

    @PostMapping
    public ResponseEntity<String> addCity(@RequestBody City city){
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.addCity(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody City city){
        return ResponseEntity.ok(cityService.updateCity(id, city));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id){
        return ResponseEntity.ok(cityService.deleteCity(id));
    }
}
