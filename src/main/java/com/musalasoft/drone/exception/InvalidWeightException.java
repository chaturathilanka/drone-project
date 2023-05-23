package com.musalasoft.drone.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidWeightException extends RuntimeException {
    private final List<String> medicationNames;

    public InvalidWeightException(String message, List<String> medicationNames) {
        super(message);
        this.medicationNames = medicationNames;
    }
}
