package com.musalasoft.drone.auditlog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "drone_audit_log")
@Getter
@Setter
public class DroneAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long droneId;

    private String action;

    private String details;

    @Override
    public String toString() {
        return "DroneAuditLog{" +
                "id=" + id +
                ", droneId=" + droneId +
                ", action='" + action + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}

