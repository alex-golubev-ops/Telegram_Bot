package com.golubev.telegrambot.webservice.exception;

public class CityAlreadyExistExeption extends RuntimeException {

    public CityAlreadyExistExeption(String message) {
        super(message);
    }

    public CityAlreadyExistExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
