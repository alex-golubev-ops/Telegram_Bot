package com.golubev.telegrambot.webservice.service;

import com.golubev.telegrambot.webservice.entity.City;
import com.golubev.telegrambot.webservice.exception.NotFoundCityException;
import com.golubev.telegrambot.webservice.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findCityById(Integer id){
        return cityRepository.findById(id).orElseThrow(()->new NotFoundCityException("City with id = "+id+" is not exist"));
    }
    public List<City> findAllCity(){
        return cityRepository.findAll();
    }

}
