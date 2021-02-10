package com.golubev.telegrambot.webservice.exception;

public class NotFoundCityException extends RuntimeException {

    public NotFoundCityException(String message) {
        super(message);
    }

    public NotFoundCityException(String message, Throwable cause) {
        super(message, cause);
    }


}
