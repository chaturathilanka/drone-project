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

    @Pattern(regexp = "^[A-Za-z0-9-_]+$")
    private String name;

    private double weight;

    @Pattern(regexp = "^[A-Z_0-9]+$")
    private String code;

    @Lob
    private byte[] image;

    @ManyToOne
    private Drone drone;

}
