package org.vitacare.professionalservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "specialties", schema = "professionals_service")
@Data
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 512)
    private String description;
}