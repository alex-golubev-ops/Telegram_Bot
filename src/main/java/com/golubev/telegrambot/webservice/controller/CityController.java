package com.golubev.telegrambot.webservice.controller;

import com.golubev.telegrambot.webservice.entity.City;
import com.golubev.telegrambot.webservice.exception.NotFoundCityException;
import com.golubev.telegrambot.webservice.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public List<City> findAllCity(){
        return cityService.findAllCity();
    }


}
