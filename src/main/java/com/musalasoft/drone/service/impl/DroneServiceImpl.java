package com.musalasoft.drone.service.impl;

import com.musalasoft.drone.exception.DroneNotFoundException;
import com.musalasoft.drone.exception.InvalidWeightException;
import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.DroneState;
import com.musalasoft.drone.model.Medication;
import com.musalasoft.drone.repository.DroneRepository;
import com.musalasoft.drone.repository.MedicationRepository;
import com.musalasoft.drone.service.DroneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    public DroneServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public void loadMedications(Long droneId, List<Medication> medications) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new DroneNotFoundException("Drone not found with ID: " + droneId));

        double totalWeight = medications.stream()
                .mapToDouble(Medication::getWeight)
                .sum();

        if (totalWeight > drone.getWeightLimit()) {
            throw new InvalidWeightException("Total weight exceeds drone's weight limit." , medications.stream().map(Medication::getName).collect(Collectors.toList()));
        }

        medications.forEach(drone::addMedication);
        drone.setState(DroneState.LOADED);
        droneRepository.save(drone);
    }

    @Override
    public List<Medication> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new DroneNotFoundException("Drone not found with ID: " + droneId));

        if (drone.getState() != DroneState.LOADED) {
            throw new IllegalStateException("Drone is not in the LOADED state.");
        }

        return drone.getMedications();
    }

    @Override
    public List<Drone> getAvailableDrones() {
        return droneRepository.findByState(DroneState.IDLE);
    }
}
