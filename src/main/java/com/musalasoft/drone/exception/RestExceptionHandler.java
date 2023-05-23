package com.musalasoft.drone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DroneNotFoundException.class)
    public ResponseEntity<Object> handleDroneNotFoundException(DroneNotFoundException ex) {
        String errorMessage = "Drone not found: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(InvalidWeightException.class)
    public ResponseEntity<Object> handleInvalidWeightException(InvalidWeightException ex) {
        String errorMessage = "Invalid weight for medication '" + ex.getMedicationNames() + "': " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(LowBatteryException.class)
    public ResponseEntity<Object> handleLowBatteryException(LowBatteryException ex) {
        String errorMessage = "Low battery level: " + ex.getBatteryLevel() + "%. " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}

