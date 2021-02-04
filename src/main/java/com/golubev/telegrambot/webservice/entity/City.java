package com.golubev.telegrambot.webservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<CityInfo> cityInfos;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
