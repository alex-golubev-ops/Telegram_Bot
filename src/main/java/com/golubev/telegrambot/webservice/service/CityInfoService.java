package com.golubev.telegrambot.webservice.service;

import com.golubev.telegrambot.webservice.entity.City;
import com.golubev.telegrambot.webservice.entity.CityInfo;
import com.golubev.telegrambot.webservice.repository.CityInfoRepository;
import com.golubev.telegrambot.webservice.repository.CityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityInfoService {

    private final CityInfoRepository cityInfoRepository;

    private final CityRepository cityRepository;
    public CityInfoService(CityInfoRepository cityInfoRepository, CityRepository cityRepository) {
        this.cityInfoRepository = cityInfoRepository;
        this.cityRepository = cityRepository;
    }

    public List<String> allCityInfoByCityName(String name){
        Optional<City> cityFromBd = cityRepository.findByName(name);
        if(cityFromBd.isPresent()){
            List<String> information = cityFromBd
                    .get()
                    .getCityInfos()
                    .stream()
                    .map(CityInfo::getInformation)
                    .collect(Collectors.toList());
            return information;
        }
        return null;
    }

    public void delete(Integer cityId, Integer infoId) {
        cityInfoRepository.deleteByIdAndCity_id(infoId, cityId);
    }
}
