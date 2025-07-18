package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dto.request.PlanosRequest;
import org.vitacare.exceptions.planosExceptions.PlanoNaoExisteExceptions;
import org.vitacare.model.ConvenioModel;
import org.vitacare.model.Planos;
import org.vitacare.repository.ConvenioRepository;
import org.vitacare.repository.PlanoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final ObjectMapper objectMapper;
    private final ConvenioService convenioService;

    public List<Planos> listaPlanos() {
        return planoRepository.findAll();
    }

    public Planos buscarPlanoPorId(Long id) throws Exception{
        verificarPlanoExiste(id);
        return planoRepository.findById(id).get();
    }

    public void verificarPlanoExiste(Long id) throws Exception {
        if (!planoRepository.existsById(id)) {
            throw new PlanoNaoExisteExceptions();
        }
    }

    public ConvenioModel buscarConvenioPorId(Long id) throws Exception{
        convenioService.verificarConvenioExiste(id);
        return convenioService.buscarConvenioPorId(id);
    }

    public void cadastrarPlano(PlanosRequest planosRequest) throws Exception {
        ConvenioModel convenioModel = buscarConvenioPorId(planosRequest.getIdConvenio());
        Planos planos = objectMapper.convertValue(planosRequest, Planos.class);
        planos.setConvenioModel(convenioModel);
        planoRepository.save(planos);
    }

    public void atualizarPlano(Long id, PlanosRequest planosRequest) throws Exception {
        verificarPlanoExiste(id);
        ConvenioModel convenioModel = buscarConvenioPorId(planosRequest.getIdConvenio());
        Planos planos = objectMapper.convertValue(planosRequest, Planos.class);
        planos.setConvenioModel(convenioModel);
        planos.setId(id);
        planoRepository.save(planos);
    }

    public void deletarPlano(Long id) throws Exception {
        verificarPlanoExiste(id);
        planoRepository.deleteById(id);
    }



}
