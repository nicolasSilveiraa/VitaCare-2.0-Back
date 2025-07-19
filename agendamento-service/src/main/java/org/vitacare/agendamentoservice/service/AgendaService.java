package org.vitacare.agendamentoservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.agendamentoservice.exception.InvalidRequestException;
import org.vitacare.agendamentoservice.exception.ResourceNotFoundException;
import org.vitacare.dtos.appointment.AgendaCreateRequest;
import org.vitacare.agendamentoservice.model.AgendaModel;
import org.vitacare.agendamentoservice.repository.AgendaRepository;
import org.vitacare.dtos.appointment.AgendamentoResponse;
import org.vitacare.dtos.patient.PacienteSummaryDTO;
import org.vitacare.dtos.professional.ProfessionalSummaryDTO;
import org.vitacare.dtos.professional.SpecialtySummaryDTO;
import org.vitacare.paciente.client.PacienteQueryClient;
import org.vitacare.professionals.client.ProfessionalClient;
import org.vitacare.professionals.client.SpecialtyClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ObjectMapper objectMapper;

    private final PacienteQueryClient pacienteClient;
    private final ProfessionalClient professionalClient;
    private final SpecialtyClient specialtyClient;


    public List<AgendamentoResponse> buscarTodasAgendas(){
        return agendaRepository.findAll().stream()
                .map(this::agendamentoCompleto)
                .collect(Collectors.toList());
    }


    public void verificarAgendaExiste(Long id)throws Exception{
    if (!agendaRepository.existsById(id)) {
        throw new Exception("Agenda não encontrada com o ID: " + id);
        }
    }

    public AgendamentoResponse buscarAgendaPorId(Long id) {
        AgendaModel agendamento = agendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agenda não encontrada com o ID: " + id));
        return agendamentoCompleto(agendamento);
    }

    public AgendamentoResponse adicionarAgenda(AgendaCreateRequest request) throws Exception {

        PacienteSummaryDTO paciente = validarPaciente(request.pacienteId());
        ProfessionalSummaryDTO profissional = validarProfissional(request.professionalId());
        SpecialtySummaryDTO especialidade = validarEspecialidade(request.specialtyId());

        boolean existe = agendaRepository.existsByProfissionalIdAndDataHoraAgendamento(
                request.professionalId(),
                request.agendamentoDateTime()
        );
        if (existe) {
            throw new Exception("Já existe um agendamento para este médico neste horário.");
        }
        AgendaModel novoAgendamento = objectMapper.convertValue(request, AgendaModel.class);
        agendaRepository.save(novoAgendamento);

        return new AgendamentoResponse(
                novoAgendamento.getIdAgenda(),
                novoAgendamento.getDataHoraAgendamento(),
                paciente,
                profissional,
                especialidade
        );
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

    public List<AgendamentoResponse> buscarAgendaPorMedico(Long profissionalId) {
        validarProfissional(profissionalId);
        return agendaRepository.findAllByProfissionalId(profissionalId).stream()
                .map(this::agendamentoCompleto)
                .collect(Collectors.toList());
    }

    public List<AgendamentoResponse> buscarAgendaPorEspecialidade(Integer especialidadeId) {
        validarEspecialidade(especialidadeId);
        return agendaRepository.findAllByEspecialidadeId(especialidadeId).stream()
                .map(this::agendamentoCompleto)
                .collect(Collectors.toList());
    }

    private AgendamentoResponse agendamentoCompleto(AgendaModel agendamento) {
        PacienteSummaryDTO patient = pacienteClient.getPacienteSummaryById(agendamento.getPacienteId());
        ProfessionalSummaryDTO professional = professionalClient.getProfessionalSummaryById(agendamento.getProfissionalId());
        SpecialtySummaryDTO specialty = specialtyClient.getSpecialtySummaryById(agendamento.getEspecialidadeId());

        return new AgendamentoResponse(
                agendamento.getIdAgenda(),
                agendamento.getDataHoraAgendamento(),
                patient,
                professional,
                specialty
        );
    }

    private PacienteSummaryDTO validarPaciente(Long id) {
        try {
            return pacienteClient.getPacienteSummaryById(id);
        } catch (FeignException.NotFound e) {
            throw new InvalidRequestException("Paciente com ID " + id + " não encontrado.");
        }
    }

    private ProfessionalSummaryDTO validarProfissional(Long id) {
        try {
            return professionalClient.getProfessionalSummaryById(id);
        } catch (FeignException.NotFound e) {
            throw new InvalidRequestException("Profissional com ID " + id + " não encontrado.");
        }
    }

    private SpecialtySummaryDTO validarEspecialidade(Integer id) {
        try {
            return specialtyClient.getSpecialtySummaryById(id);
        } catch (FeignException.NotFound e) {
            throw new InvalidRequestException("Especialidade com ID " + id + " não encontrada.");
        }
    }
}

