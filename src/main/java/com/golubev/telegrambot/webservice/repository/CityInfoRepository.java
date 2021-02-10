package com.golubev.telegrambot.webservice.repository;

import com.golubev.telegrambot.webservice.entity.City;
import com.golubev.telegrambot.webservice.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CityInfoRepository extends JpaRepository<CityInfo, Integer> {

    @Transactional
    void deleteByIdAndCity_id(Integer infoId, Integer cityId);

}
