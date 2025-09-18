package com.ecampo.api.rutabackends.excepcions;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
