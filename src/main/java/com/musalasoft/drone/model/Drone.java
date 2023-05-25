package com.musalasoft.drone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @Size(max = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Max(value = 500)
    private double weightLimit;

    @Min(value = 0)
    @Max(value = 100)
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany(mappedBy = "drone")
    private List<Medication> medications;
    public void addMedication(Medication medication) {
        if (medications == null) {
            medications = new ArrayList<>();
        }
        medications.add(medication);
        medication.setDrone(this);
    }

    public void removeMedication(Medication medication) {
        if (medications != null) {
            medications.remove(medication);
            medication.setDrone(null);
        }
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                ", medications=" + medications +
                '}';
    }
}
