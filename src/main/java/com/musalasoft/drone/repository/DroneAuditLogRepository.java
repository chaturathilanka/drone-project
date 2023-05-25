package com.musalasoft.drone.repository;

import com.musalasoft.drone.auditlog.DroneAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneAuditLogRepository extends JpaRepository<DroneAuditLog, Long> {

}