package com.golubev.telegrambot.webservice.service;

import com.golubev.telegrambot.webservice.repository.CityInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class CityInfoService {

    private final CityInfoRepository cityInfoRepository;

    public CityInfoService(CityInfoRepository cityInfoRepository) {
        this.cityInfoRepository = cityInfoRepository;
    }

    public void delete(Integer cityId, Integer infoId) {
        cityInfoRepository.deleteByIdAndCity_id(infoId, cityId);
    }
}
