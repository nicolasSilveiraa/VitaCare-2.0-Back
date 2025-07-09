package org.vitacare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.vitacare.model.PacienteModel;

public interface PaginacaoRepository extends PagingAndSortingRepository<PacienteModel, Long> {

}
