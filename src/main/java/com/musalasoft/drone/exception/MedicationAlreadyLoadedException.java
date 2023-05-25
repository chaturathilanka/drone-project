package com.musalasoft.drone.exception;

public class MedicationAlreadyLoadedException extends RuntimeException {
    public MedicationAlreadyLoadedException(String message) {
        super(message);
    }
}
