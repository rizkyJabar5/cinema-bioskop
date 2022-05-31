package com.rizky.cinematic.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(String msg) {
        super(msg);
    }

    public NotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
