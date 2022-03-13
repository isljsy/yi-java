package com.ljsy.yisystem.exception;

public class InvalidJwtAuthenticationException extends Exception{

    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
