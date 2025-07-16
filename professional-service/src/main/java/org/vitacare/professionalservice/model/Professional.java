package org.vitacare.professionalservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professionals", schema = "professionals_service")
@Data
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String professionalLicense;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professionals_specialties",
            schema = "professionals_service",
            joinColumns = @JoinColumn(name = "professional_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )

    private Set<Specialty> specialties = new HashSet<>();
}
