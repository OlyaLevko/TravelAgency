package com.itacademy.exception;

/**
 * This type exception can be thrown within methods when entity with given id doesn't exist in DB.
 */
public class NotSuchElementException extends RuntimeException{

    public NotSuchElementException(String s) {
        super(s);
    }
}
