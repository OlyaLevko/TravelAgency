package com.itacademy.exception;

public class NullEntityReferenceException extends RuntimeException{

    public NullEntityReferenceException(){}

    public NullEntityReferenceException(String s){
        super(s);
    }
}
