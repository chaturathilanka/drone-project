package com.musalasoft.drone.task;

import com.musalasoft.drone.repository.DroneRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatteryCheckTask {
    private final DroneRepository droneRepository;

    public BatteryCheckTask(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Scheduled(cron = "0 0 * * * *") // Runs every hour
    public void runBatteryCheck() {

    }
}
