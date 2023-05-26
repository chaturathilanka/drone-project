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
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(InvalidWeightException.class)
    public ResponseEntity<Object> handleInvalidWeightException(InvalidWeightException ex) {
        String errorMessage =  ex.getMedicationNames() + "': " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(LowBatteryException.class)
    public ResponseEntity<Object> handleLowBatteryException(LowBatteryException ex) {
        String errorMessage = "Low battery level: " + ex.getBatteryLevel() + "%. " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(MedicationNotFoundException.class)
    public ResponseEntity<Object> handleMedicationNotFoundException(MedicationNotFoundException ex) {
        String errorMessage = "Medication not found: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @ExceptionHandler(MedicationAlreadyLoadedException.class)
    public ResponseEntity<Object> handleMedicationAlreadyLoadedException(MedicationAlreadyLoadedException ex) {
        String errorMessage = "Medication already loaded: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        String errorMessage = "Drone is not in the LOADED state: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}

