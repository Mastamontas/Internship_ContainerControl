package com.DEVLOP.Web.ExceptionHandlers;


import com.DEVLOP.CustomExceptions.*;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;


//todo: repensar este global controller porque a map so da para 10 entries - mudar para map of entries

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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
                    EquipmentClassNotFoundException.class,(ex,req) -> new ApiErrorResponse(
                           "EQUIPMENT_CLASS_NOT_FOUND",
                           ex.getMessage(),
                           req.getRequestURI(),
                           Instant.now()
                    ),
                    EquipmentConditionNotFoundException.class,(ex,req)-> new ApiErrorResponse(
                            "EQUIPMENT_CONDITION_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    EquipmentLeasingNotFoundException.class,(ex, req)-> new ApiErrorResponse(
                            "EQUIPMENT_LEASING_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    EquipmentServiceNotFoundException.class,(ex, req)-> new ApiErrorResponse(
                            "EQUIPMENT_SERVICE_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    EquipmentTypeNotFoundException.class,(ex, req)-> new ApiErrorResponse(
                            "EQUIPMENT_TYPE_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    EquipmentStatusNotFoundException.class,(ex, req)-> new ApiErrorResponse(
                            "EQUIPMENT_STATUS_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    MovementTypeNotFoundException.class,(ex, req)-> new ApiErrorResponse(
                            "MOVEMENT_TYPE_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    ),
                    TransportMeansNotFoundException.class,(ex, req)-> new ApiErrorResponse(
                            "TRANSPORT_MEANS_NOT_FOUND",
                            ex.getMessage(),
                            req.getRequestURI(),
                            Instant.now()
                    )
            );


    private static final Map<Class<? extends Throwable>, HttpStatus> STATUS_CODES =
            Map.of(
                    EquipmentNotFoundException.class, HttpStatus.NOT_FOUND,
                    MovementNotFoundException.class, HttpStatus.NOT_FOUND,
                    EquipmentClassNotFoundException.class, HttpStatus.NOT_FOUND,
                    EquipmentConditionNotFoundException.class, HttpStatus.NOT_FOUND,
                    EquipmentLeasingNotFoundException.class, HttpStatus.NOT_FOUND,
                    EquipmentServiceNotFoundException.class, HttpStatus.NOT_FOUND,
                    EquipmentTypeNotFoundException.class, HttpStatus.NOT_FOUND,
                    EquipmentStatusNotFoundException.class, HttpStatus.NOT_FOUND,
                    MovementTypeNotFoundException.class, HttpStatus.NOT_FOUND,
                    TransportMeansNotFoundException.class,HttpStatus.NOT_FOUND
            );

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorResponse> HandleAllExceptions(Throwable ex, HttpServletRequest request){
        Throwable rootCause = ex instanceof CompletionException ? ex.getCause(): ex;

        BiFunction<Throwable, HttpServletRequest, ApiErrorResponse> handler =
                ERROR_HANDLES.getOrDefault(rootCause.getClass(),(e,req) ->new ApiErrorResponse(
                        "INTERNAL_ERROR",
                        "An unexpected error has occurred",
                        req.getRequestURI(),
                        Instant.now()
        ));

        HttpStatus status = STATUS_CODES.getOrDefault(rootCause.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);

        logger.error("Exception caught: {} at URI {} -> {}", rootCause.getClass().getSimpleName(), request.getRequestURI(), rootCause.getMessage(), rootCause);
        return ResponseEntity.status(status).body(handler.apply(rootCause,request));
    }
}