package com.musalasoft.drone.model;

import jakarta.persistence.*;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private int weightLimit;

    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

}
