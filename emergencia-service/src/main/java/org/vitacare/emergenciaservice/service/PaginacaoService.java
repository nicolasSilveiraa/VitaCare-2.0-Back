package org.vitacare.emergenciaservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vitacare.emergenciaservice.model.PacienteModel;
import org.vitacare.emergenciaservice.repository.PaginacaoRepository;

@Service
@RequiredArgsConstructor
public class PaginacaoService {

    private final PaginacaoRepository paginacaoRepository;


    public Page<PacienteModel> paginacaoPacienteRequests(Pageable pageable) {
        return paginacaoRepository.findAll(pageable);
    }

}
