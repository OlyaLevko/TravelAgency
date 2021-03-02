package com.itacademy.exception;

public class RepositoryException extends RuntimeException{
    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }
}
