package com.musalasoft.drone.controller;

import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.Medication;
import com.musalasoft.drone.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones/api/v1")
public class DroneController {
    private static final Logger logger = LoggerFactory.getLogger(DroneController.class);
    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/register")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone) {
        logger.info("Registering a new drone: {}", drone);
        Drone registeredDrone = droneService.registerDrone(drone);
        return ResponseEntity.ok(registeredDrone);
    }

    @PostMapping("/{droneId}/load")
    public ResponseEntity<Void> loadMedications(
            @PathVariable Long droneId,
            @RequestBody List<Long> medicationIds
    ) {
        logger.info("Loading medications to drone with ID {}: {}", droneId, medicationIds);
        droneService.loadMedications(droneId, medicationIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{droneId}/medications")
    public ResponseEntity<List<String>> getLoadedMedications(@PathVariable Long droneId) {
        logger.info("Fetching loaded medications for drone with ID: {}", droneId);
        List<String> loadedMedications = droneService.getLoadedMedications(droneId);
        return ResponseEntity.ok(loadedMedications);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        logger.info("Fetching available drones");
        List<Drone> availableDrones = droneService.getAvailableDrones();
        return ResponseEntity.ok(availableDrones);
    }

    @GetMapping("/{droneId}/battery-level")
    public ResponseEntity<Integer> getDroneBatteryLevel(@PathVariable Long droneId) {
        logger.info("Fetching drone battery level");
        int batteryLevel = droneService.getDroneBatteryLevel(droneId);
        return ResponseEntity.ok(batteryLevel);
    }
}