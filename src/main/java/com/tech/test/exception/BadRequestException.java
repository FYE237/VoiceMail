package com.tech.test.exception;

public class BadRequestException extends RuntimeException{
	public BadRequestException(String message){
        super(message);
    }
}
