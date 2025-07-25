package org.vitacare.emergenciaservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vitacare.dtos.patient.PacienteSummaryDTO;
import org.vitacare.dtos.patient.emergencia.*;
import org.vitacare.emergenciaservice.exception.*;
import org.vitacare.emergenciaservice.model.AtendimentoEmergenciaModel;
import org.vitacare.emergenciaservice.repository.AtendimentoEmergenciaRepository;
import org.vitacare.paciente.client.PacienteQueryClient;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmergenciaService {

    private final AtendimentoEmergenciaRepository atendimentoRepository;
    private final PacienteQueryClient pacienteClient;

    public AtendimentoEmergenciaResponse darEntradaPaciente(Long pacienteId) {

        PacienteSummaryDTO pacienteDTO = validarPaciente(pacienteId);

        AtendimentoEmergenciaModel novoAtendimento = new AtendimentoEmergenciaModel();
        novoAtendimento.setPacienteId(pacienteId);
        novoAtendimento.setStatus(StatusAtendimento.AGUARDANDO_TRIAGEM);

        AtendimentoEmergenciaModel atendimentoSalvo = atendimentoRepository.save(novoAtendimento);

        return new AtendimentoEmergenciaResponse(
                atendimentoSalvo.getId(),
                atendimentoSalvo.getDataHoraChegada(),
                atendimentoSalvo.getStatus(),
                atendimentoSalvo.getQueixas(),
                atendimentoSalvo.getAlergias(),
                atendimentoSalvo.getDiagnostico(),
                atendimentoSalvo.getPrescricao(),
                pacienteDTO
        );
    }

    public AtendimentoEmergenciaResponse realizarTriagem(Long atendimentoId, TriagemRequest request) {
        AtendimentoEmergenciaModel atendimento = buscarAtendimentoOuFalhar(atendimentoId);

        if (atendimento.getStatus() != StatusAtendimento.AGUARDANDO_TRIAGEM) {
            throw new InvalidRequestException("Este atendimento não está mais aguardando triagem.");
        }

        atendimento.setQueixas(request.queixas());
        atendimento.setAlergias(request.alergias());
        atendimento.setStatus(StatusAtendimento.AGUARDANDO_CONSULTA);

        AtendimentoEmergenciaModel atendimentoAtualizado = atendimentoRepository.save(atendimento);
        return enriquecerResposta(atendimentoAtualizado);
    }

    public AtendimentoEmergenciaResponse realizarConsulta(Long atendimentoId, ConsultaRequest request) {
        AtendimentoEmergenciaModel atendimento = buscarAtendimentoOuFalhar(atendimentoId);

        if (atendimento.getStatus() != StatusAtendimento.AGUARDANDO_CONSULTA) {
            throw new InvalidRequestException("Este atendimento não está mais aguardando consulta.");
        }

        atendimento.setDiagnostico(request.diagnostico());
        atendimento.setPrescricao(request.prescricao());
        atendimento.setStatus(StatusAtendimento.CONSULTA_REALIZADA);

        AtendimentoEmergenciaModel atendimentoAtualizado = atendimentoRepository.save(atendimento);
        return enriquecerResposta(atendimentoAtualizado);
    }

    public Page<FilaAtendimentoResponse> buscarFilaPorStatusPaginado(StatusAtendimento status, Pageable pageable) {

        Page<AtendimentoEmergenciaModel> paginaDeAtendimentos = atendimentoRepository.findByStatus(status, pageable);

        return paginaDeAtendimentos.map(atendimento -> {
            PacienteSummaryDTO paciente = validarPaciente(atendimento.getPacienteId());
       return new FilaAtendimentoResponse(
                    atendimento.getId(),
                    atendimento.getDataHoraChegada(),
                    paciente
            );
        });
    }

    public List<AtendimentoEmergenciaResponse> buscarTodosAtendimentos() {
        List<AtendimentoEmergenciaModel> atendimentos = atendimentoRepository.findAll();
        return atendimentos.stream()
                .map(this::enriquecerResposta)
                .collect(Collectors.toList());
    }

    public AtendimentoEmergenciaResponse buscarAtendimentoPorId(Long id) {
        AtendimentoEmergenciaModel atendimento = buscarAtendimentoOuFalhar(id);
        return enriquecerResposta(atendimento);
    }

    public List<PacienteSummaryDTO> listarPacientesDisponiveis() {
        return pacienteClient.getAllPacientes();
    }

    private AtendimentoEmergenciaModel buscarAtendimentoOuFalhar(Long id) {
        return atendimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento de emergência com ID " + id + " não encontrado."));
    }

    private PacienteSummaryDTO validarPaciente(Long pacienteId) {
        try {
            return pacienteClient.getPacienteSummaryById(pacienteId);
        } catch (FeignException.NotFound e) {
            throw new InvalidRequestException("Paciente com ID " + pacienteId + " não encontrado no serviço de cadastro.");
        }
    }
    private AtendimentoEmergenciaResponse enriquecerResposta(AtendimentoEmergenciaModel atendimento) {
        PacienteSummaryDTO paciente = validarPaciente(atendimento.getPacienteId());

        return new AtendimentoEmergenciaResponse(
                atendimento.getId(),
                atendimento.getDataHoraChegada(),
                atendimento.getStatus(),
                atendimento.getQueixas(),
                atendimento.getAlergias(),
                atendimento.getDiagnostico(),
                atendimento.getPrescricao(),
                paciente
        );
    }
}
