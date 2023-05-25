package com.musalasoft.drone.service.impl;

import com.musalasoft.drone.controller.DroneController;
import com.musalasoft.drone.exception.DroneNotFoundException;
import com.musalasoft.drone.exception.InvalidWeightException;
import com.musalasoft.drone.exception.MedicationAlreadyLoadedException;
import com.musalasoft.drone.exception.MedicationNotFoundException;
import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.DroneState;
import com.musalasoft.drone.model.Medication;
import com.musalasoft.drone.repository.DroneRepository;
import com.musalasoft.drone.repository.MedicationRepository;
import com.musalasoft.drone.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private static final Logger logger = LoggerFactory.getLogger(DroneServiceImpl.class);

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
    public void loadMedications(Long droneId, List<Long> medicationIds) {

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new DroneNotFoundException("Drone not found with ID: " + droneId));

        double totalWeight = 0.0;
        List<Medication> medications = new ArrayList<>();

        for (Long medicationId : medicationIds) {
            Medication medication = medicationRepository.findById(medicationId)
                    .orElseThrow(() -> new MedicationNotFoundException("Medication not found with ID: " + medicationId));

            if (drone.getMedications().contains(medication)) {
                throw new MedicationAlreadyLoadedException("Medication with ID " + medicationId + " is already loaded on the drone.");
            }
            totalWeight += medication.getWeight();
            medications.add(medication);
        }
        if (totalWeight > drone.getWeightLimit()) {
            throw new InvalidWeightException("Total weight exceeds drone's weight limit.", medications.stream().map(Medication::getName).collect(Collectors.toList()));
        }

        medications.forEach(drone::addMedication);
        drone.setState(DroneState.LOADED);
        droneRepository.save(drone);
    }

    @Override
    public List<String> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new DroneNotFoundException("Drone not found with ID: " + droneId));

        if (drone.getState() != DroneState.LOADED) {
            throw new IllegalStateException();
        }
        List<String> medicationNames = drone.getMedications().stream().map(Medication::getName).collect(Collectors.toList());
        return medicationNames;
    }

    @Override
    public List<Drone> getAvailableDrones() {
        return droneRepository.findByState(DroneState.IDLE);
    }

    @Override
    public int getDroneBatteryLevel(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new DroneNotFoundException("Drone not found with ID: " + droneId));

        int batteryLevel = drone.getBatteryCapacity();
        logger.info("Drone {} battery level: {}%", droneId, batteryLevel);

        return batteryLevel;
    }

}
