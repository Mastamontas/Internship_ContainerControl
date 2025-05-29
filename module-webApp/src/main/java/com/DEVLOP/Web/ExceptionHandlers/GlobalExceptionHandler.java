package com.DEVLOP.Web.ExceptionHandlers;


import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Repositories.MovementTypeRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Throwable>, BiFunction<Throwable, HttpServletRequest, ApiErrorResponse>> ERROR_HANDLES =
            Map.of(
                    EquipmentNotFoundException.class, (ex, req) -> new ApiErrorResponse(
                            "EQUIPMENT_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    MovementNotFoundException.class,(ex, req) -> new ApiErrorResponse(
                            "MOVEMENT_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    IllegalArgumentException.class,(ex,req) ->new ApiErrorResponse(
                            "INVALID_ARGUMENT",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    )
            );


    private static final Map<Class<? extends Throwable>, HttpStatus> STATUS_CODES =
            Map.of(
                    EquipmentNotFoundException.class, HttpStatus.NOT_FOUND,
                    MovementNotFoundException.class, HttpStatus.NOT_FOUND,
                    IllegalArgumentException.class, HttpStatus.BAD_REQUEST
            );

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorResponse> HandleAllExceptions(Throwable ex, HttpServletRequest request){
        Throwable rootCause = ex instanceof CompletionException ? ex.getCause(): ex;
        BiFunction<Throwable, HttpServletRequest, ApiErrorResponse> handler =
                ERROR_HANDLES.getOrDefault(rootCause.getClass(),(e,req) ->new ApiErrorResponse(
                        "INTERNAL_ERROR", "An unexpected error has occured",
                req.getRequestURI(), Instant.now()
        ));
        HttpStatus status = STATUS_CODES.getOrDefault(rootCause.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(status).body(handler.apply(rootCause,request));
    }
}