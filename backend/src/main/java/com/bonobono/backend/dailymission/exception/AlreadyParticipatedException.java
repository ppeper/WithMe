package com.bonobono.backend.dailymission.exception;

public class AlreadyParticipatedException extends RuntimeException {
    public AlreadyParticipatedException(String message) {
        super(message);
    }
}
