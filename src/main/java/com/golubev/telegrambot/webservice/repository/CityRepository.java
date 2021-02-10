package com.golubev.telegrambot.webservice.repository;

import com.golubev.telegrambot.webservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    Optional<City> findByName(String name);
}
