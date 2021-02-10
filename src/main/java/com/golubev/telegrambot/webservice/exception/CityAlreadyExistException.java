package com.golubev.telegrambot.webservice.exception;

public class CityAlreadyExistException extends RuntimeException {

    public CityAlreadyExistException(String message) {
        super(message);
    }

    public CityAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
