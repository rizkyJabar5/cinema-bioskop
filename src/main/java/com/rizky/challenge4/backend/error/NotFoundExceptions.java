package com.rizky.challenge4.backend.error;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(String msg) {
    }

    Throwable throwable(){
        return getCause();
    }
}
