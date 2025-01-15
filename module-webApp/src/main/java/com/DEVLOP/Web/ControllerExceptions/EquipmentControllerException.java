package com.DEVLOP.Web.ControllerExceptions;


import com.DEVLOP.ContainerMovements.CustomExceptions.EquipmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class is annotated with {@link ControllerAdvice}, allowing it to handle exceptions
 * across multiple controllers and map them to appropriate HTTP responses.
 */
@ControllerAdvice
public class EquipmentControllerException {

    /**
     * Handles {@link EquipmentNotFoundException} and returns a 404 NOT FOUND HTTP response.
     * This method maps the exception to an HTTP response with the exception's message as the response body.
     *
     * @param ex The exception object containing details of the error.
     * @return A {@link ResponseEntity} containing the HTTP status (404) and the exception message.
     */
    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<String> handleEquipmentListNotFound(EquipmentNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
