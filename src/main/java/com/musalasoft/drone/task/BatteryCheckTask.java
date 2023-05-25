package com.musalasoft.drone.task;

import com.musalasoft.drone.auditlog.DroneAuditLog;
import com.musalasoft.drone.model.Drone;
import com.musalasoft.drone.model.DroneState;
import com.musalasoft.drone.repository.DroneAuditLogRepository;
import com.musalasoft.drone.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatteryCheckTask {
    private static final Logger logger = LoggerFactory.getLogger(BatteryCheckTask.class);
    private final DroneRepository droneRepository;
    private final DroneAuditLogRepository droneAuditLogRepository;


    public BatteryCheckTask(DroneRepository droneRepository, DroneAuditLogRepository droneAuditLogRepository) {
        this.droneRepository = droneRepository;
        this.droneAuditLogRepository = droneAuditLogRepository;
    }

    @Scheduled(cron = "0 * * * * *") // Runs every minute
    public void runBatteryCheck() {
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {
            int batteryLevel = drone.getBatteryCapacity();

            if (batteryLevel < 25) {
                logger.info("Drone {} has low battery level", drone.getId());
                if (drone.getState() == DroneState.LOADING) {
                    // Battery recharge logic comes here
                    drone.setBatteryCapacity(100); // Set the battery level to 100% after recharge
                    drone.setState(DroneState.IDLE); // Set the drone state back to IDLE
                    droneRepository.save(drone); // Save the updated drone entity
                    DroneAuditLog auditLog = new DroneAuditLog();
                    auditLog.setDroneId(drone.getId());
                    auditLog.setAction("Low Battery Check");
                    auditLog.setDetails("Battery level: " + batteryLevel);

                    droneAuditLogRepository.save(auditLog);
                }
            }
        }

    }
}
