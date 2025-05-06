package com.boardcamp.api.exceptions;

public class GenericUnprocessableEntityException extends RuntimeException {

    public  GenericUnprocessableEntityException(String message) {
        super(message);
    }
}
