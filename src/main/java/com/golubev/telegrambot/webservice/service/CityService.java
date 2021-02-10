package com.golubev.telegrambot.webservice.service;

import com.golubev.telegrambot.webservice.dto.CityDto;
import com.golubev.telegrambot.webservice.entity.City;
import com.golubev.telegrambot.webservice.exception.NotFoundCityException;
import com.golubev.telegrambot.webservice.exception.CityAlreadyExistException;
import com.golubev.telegrambot.webservice.repository.CityRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findCityById(Integer id) {
        return cityRepository.findById(id).orElseThrow(() -> new NotFoundCityException("City with id = " + id + " is not exist"));
    }

    public List<City> findAllCity() {
        return cityRepository.findAll();
    }

    public City addCity(CityDto cityDto){
        City city = new City();
        city.setName(cityDto.getName());
        city.setCityInfos(cityDto.getCityInfos());
        List<City> allCityFromBd = cityRepository.findAll();
        Optional<City> any = allCityFromBd
                .stream()
                .filter(e -> e.getName().equals(city.getName()))
                .findAny();
        if(any.isPresent()){
            throw new CityAlreadyExistException("City with name "+city.getName()+" already exist");
        }
        cityRepository.save(city);
        return city;
    }


    public City updateCity(Integer id, CityDto cityDto) {

        Optional<City> cityFromBd = cityRepository.findById(id);

        City city = cityFromBd.orElseThrow(
                () -> new NotFoundCityException("City with id = " + id + " is not exist"));

        if(cityDto.getName()!=null){
            city.setName(cityDto.getName());
        }

        city.setCityInfos(Stream
                .of(city.getCityInfos(), cityDto.getCityInfos())
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .distinct()
                .filter(Objects::nonNull)
                .peek(c -> c.setCity(city))
                .collect(Collectors.toList())
        );

        cityRepository.save(city);
        return city;
    }

    public void deleteCity(Integer id) {
        Optional<City> cityFromBd = cityRepository.findById(id);
        City city = cityFromBd.orElseThrow(
                () -> new NotFoundCityException("City with id = " + id + " is not exist"));
        cityRepository.delete(city);

    }

    public Optional<City> findCityByName(String name){
        return cityRepository.findByName(name);
    }
}
