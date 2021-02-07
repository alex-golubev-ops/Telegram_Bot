package com.golubev.telegrambot.webservice.exception;

import java.util.function.Supplier;

public class NotFoundCityException extends RuntimeException  {
    public NotFoundCityException(String message) {
        super(message);
    }

    public NotFoundCityException(String message, Throwable cause) {
        super(message, cause);
    }


}
