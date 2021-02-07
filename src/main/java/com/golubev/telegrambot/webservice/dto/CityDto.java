package com.golubev.telegrambot.webservice.dto;

import com.golubev.telegrambot.webservice.entity.CityInfo;

import java.util.List;

public class CityDto {

    private String name;

    private List<CityInfo> cityInfos;

    public CityDto() {

    }

    public CityDto(String name, List<CityInfo> cityInfos) {
        this.name = name;
        this.cityInfos = cityInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityInfo> getCityInfos() {
        return cityInfos;
    }

    public void setCityInfos(List<CityInfo> cityInfos) {
        this.cityInfos = cityInfos;
    }
}
