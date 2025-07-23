package org.vitacare.emergenciaservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.vitacare.emergenciaservice.model.AtendimentoEmergenciaModel;

public interface PaginacaoRepository extends PagingAndSortingRepository<AtendimentoEmergenciaModel, Long> {

}
