package org.vitacare.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.vitacare.model.Enum.StatusDoPaciente;
import org.vitacare.model.PacienteModel;

import java.time.LocalDate;
import java.util.Locale;

public class PacienteSpecification {

    public static Specification<PacienteModel> filtro (Long idPaciente, String nomePaciente,
                                                       LocalDate dataNascimento, String cpfPaciente,
                                                       StatusDoPaciente statusDoPaciente) {

        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (idPaciente != null) {
                p = cb.and(p, cb.equal(root.get("idPaciente"), idPaciente));
            }
            if (nomePaciente != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("nomePaciente")), "%" + nomePaciente.toLowerCase() + "%"));
            }
            if (dataNascimento != null) {
                p = cb.and(p, cb.equal(root.get("dataNascimento"), dataNascimento));
            }
            if (cpfPaciente != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("cpfPaciente")), "%" + cpfPaciente.toLowerCase() + "%"));
            }
            if (statusDoPaciente != null){
                p = cb.and(p, cb.equal(root.get("statusPaciente"), statusDoPaciente));
            }
            return p;
        };
    }

}
