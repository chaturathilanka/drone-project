package com.musalasoft.drone.exception;

public class LowBatteryException extends RuntimeException {
    private final int batteryLevel;

    public LowBatteryException(String message, int batteryLevel) {
        super(message);
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}
