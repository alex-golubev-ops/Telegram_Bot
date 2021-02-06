package com.golubev.telegrambot.webservice.service;

import com.golubev.telegrambot.webservice.entity.City;
import com.golubev.telegrambot.webservice.exception.NotFoundCityException;
import com.golubev.telegrambot.webservice.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City getCity(Integer id){
        return cityRepository.findById(id).orElseThrow(new NotFoundCityException(""));
    }
    public void rr(){

    }
}
