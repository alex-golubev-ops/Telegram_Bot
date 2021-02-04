package com.golubev.telegrambot.webservice.repository;

import com.golubev.telegrambot.webservice.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfoRepository extends JpaRepository<CityInfo, Integer> {

}
