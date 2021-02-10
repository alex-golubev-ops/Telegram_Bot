package com.golubev.telegrambot.webservice.controller;

import com.golubev.telegrambot.webservice.service.CityInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/city")
public class CityInfoController {

    private final CityInfoService cityInfoService;

    public CityInfoController(CityInfoService cityInfoService) {
        this.cityInfoService = cityInfoService;
    }

    @DeleteMapping("/{cityId}/info/{infoId}")
    public void deleteCityInfo(@PathVariable("cityId") Integer cityId,
                               @PathVariable("infoId") Integer infoId) {
        cityInfoService.delete(cityId, infoId);
    }
}
