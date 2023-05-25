package com.musalasoft.drone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9-_]+$", message = "Name should only contain alphanumeric characters, hyphens, and underscores")
    private String name;

    private double weight;

    @Pattern(regexp = "^[A-Z_0-9]+$", message = "Code should only contain uppercase alphanumeric characters and underscores")
    private String code;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinTable(
            name = "drone_medication",
            joinColumns = @JoinColumn(name = "drone_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private Drone drone;

}
