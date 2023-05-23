package com.musalasoft.drone.controller;

import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.Medication;
import com.musalasoft.drone.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {
    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/register")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone) {
        Drone registeredDrone = droneService.registerDrone(drone);
        return ResponseEntity.ok(registeredDrone);
    }

    @PostMapping("/{droneId}/load")
    public ResponseEntity<Void> loadMedications(
            @PathVariable Long droneId,
            @RequestBody List<Medication> medications
    ) {
        droneService.loadMedications(droneId, medications);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{droneId}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable Long droneId) {
        List<Medication> loadedMedications = droneService.getLoadedMedications(droneId);
        return ResponseEntity.ok(loadedMedications);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        List<Drone> availableDrones = droneService.getAvailableDrones();
        return ResponseEntity.ok(availableDrones);
    }
}