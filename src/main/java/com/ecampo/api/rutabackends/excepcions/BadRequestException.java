package com.ecampo.api.rutabackends.excepcions;

public class BadRequestException extends RuntimeException{
    
    public BadRequestException(String message) {
        super(message);
    }

}
