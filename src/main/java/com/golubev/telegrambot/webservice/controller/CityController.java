package com.golubev.telegrambot.webservice.controller;

import com.golubev.telegrambot.webservice.dto.CityDto;
import com.golubev.telegrambot.webservice.entity.City;
import com.golubev.telegrambot.webservice.exception.CityAlreadyExistExeption;
import com.golubev.telegrambot.webservice.exception.NotFoundCityException;
import com.golubev.telegrambot.webservice.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public City start(@PathVariable Integer id) {
        try {
            City city = cityService.findCityById(id);
            return city;
        } catch (NotFoundCityException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @GetMapping
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }

    @PostMapping()
    public City addCity(@RequestBody CityDto cityDto) {
        try {

            return cityService.addCity(cityDto);

        } catch (CityAlreadyExistExeption e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Integer id, @RequestBody CityDto cityDto) {
        try {

            return cityService.updateCity(id, cityDto);

        }catch (NotFoundCityException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Integer id){

        try{
            cityService.deleteCity(id);

        }catch (NotFoundCityException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
