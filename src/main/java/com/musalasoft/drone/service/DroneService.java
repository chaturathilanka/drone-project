package com.musalasoft.drone.service;

import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.Medication;

import java.util.List;

public interface DroneService {
    Drone registerDrone(Drone drone);

    void loadMedications(Long droneId, List<Long> medicationIds);

    List<String> getLoadedMedications(Long droneId);

    List<Drone> getAvailableDrones();

    int getDroneBatteryLevel(Long droneId);
}
