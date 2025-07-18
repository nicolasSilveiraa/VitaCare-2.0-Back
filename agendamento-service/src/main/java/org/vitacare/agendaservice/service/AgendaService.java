package org.vitacare.agendaservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dtos.appointment.AgendaCreateRequest;
import org.vitacare.agendaservice.model.AgendaModel;
import org.vitacare.agendaservice.repository.AgendaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ObjectMapper objectMapper;


    public List<AgendaModel> buscarAgenda(){
        return agendaRepository.findAll();
    }


    public void verificarAgendaExiste(Long id)throws Exception{
    if (!agendaRepository.existsById(id)) {
        throw new Exception("Agenda não encontrada com o ID: " + id);
    }
    }

    public AgendaModel buscarAgendaPorId(Long id) throws Exception {
        verificarAgendaExiste(id);
        return agendaRepository.findById(id).get();
    }

    public void adicionarAgenda(AgendaCreateRequest agendaCreateRequest) throws Exception {
        boolean existe = agendaRepository.existsByMedicoAndDataConsultaAndHoraConsulta(
                agendaCreateRequest.medico(),
                agendaCreateRequest.dataConsulta(),
                agendaCreateRequest.horaConsulta()
        );
        if (existe) {
            throw new Exception("Já existe um agendamento para este médico neste horário.");
        }
        AgendaModel agendaModel = objectMapper.convertValue(agendaCreateRequest, AgendaModel.class);
        agendaRepository.save(agendaModel);
    }

    public void atualizarAgenda(Long id, AgendaCreateRequest agendaCreateRequest) throws Exception {
        verificarAgendaExiste(id);
        AgendaModel agendaModel = objectMapper.convertValue(agendaCreateRequest, AgendaModel.class);
        agendaModel.setIdAgenda(id);
        agendaRepository.save(agendaModel);
    }

    public void excluirAgenda(Long id) throws Exception {
        verificarAgendaExiste(id);
        agendaRepository.deleteById(id);
    }

    public List<AgendaModel> buscarAgendaPorMedico(String medico) {
        return agendaRepository.findAllByMedico(medico);
    }

    public List<AgendaModel> buscarAgendaPorEspecialidade(String especialidade) {
        return agendaRepository.findAllByEspecialidade(especialidade);
    }


}

