package com.musalasoft.drone.exception;

public class DroneNotFoundException extends RuntimeException {

    public DroneNotFoundException(String message) {
        super(message);
    }
}