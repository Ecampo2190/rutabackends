package com.ecampo.api.rutabackends.excepcions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Se convierte en un manejador global de errores para los controladores REST
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    //indicamos la excepcion que vamos a manejar
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            "Recurso no encontrado"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(BadRequestException ex){
        
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            "Solicitud incorrecta"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationsExceptions(MethodArgumentNotValidException ex){
        
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

        String errorDetails = "Errores de validacion en los campos: " + String.join(", ", errores.keySet());
        ErrorResponse errorResponse = new ErrorResponse(
            errorDetails,
            HttpStatus.BAD_REQUEST.value(),
            "Validacion Fallida"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }    
}
