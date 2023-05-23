package com.musalasoft.drone.repository;

import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByState(DroneState state);
}
