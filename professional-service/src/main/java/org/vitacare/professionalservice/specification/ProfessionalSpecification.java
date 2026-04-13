package org.vitacare.professionalservice.specification;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.vitacare.professionalservice.model.Professional;
import org.vitacare.professionalservice.model.Specialty;

public class ProfessionalSpecification {

    public static Specification<Professional> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Professional> hasSpecialty(Integer specialtyId) {
        return (root, query, criteriaBuilder) -> {
            if (specialtyId == null) {
                return criteriaBuilder.conjunction();
            }

            Join<Professional, Specialty> specialtyJoin = root.join("specialties");

            return criteriaBuilder.equal(specialtyJoin.get("id"), specialtyId);
        };
    }
}
