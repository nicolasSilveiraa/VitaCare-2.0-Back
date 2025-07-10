package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.vitacare.dto.*;
import org.vitacare.exception.*;
import org.vitacare.model.Enum.StatusDoPaciente;
import org.vitacare.model.PacienteModel;
import org.vitacare.repository.PacienteRepository;
import org.vitacare.specification.PacienteSpecification;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository paciente;
    private final ObjectMapper objectMapper;
    private final PacienteRepository pacienteRepository;

    public List<PacienteModel> buscarPaciente(){
        return paciente.findAll();
    }

    public PacienteModel buscarPacientePorId(Long id) throws Exception {
        verificarPacienteExiste(id);
        return paciente.findById(id).get();
    }

    public void verificarPacienteExiste(Long id) throws Exception{
        if (!paciente.existsById(id)) {
            throw new PacienteNaoEncontradoException(id);
        }
    }

    private void verificarPacienteCriado(PacienteCreateRequest pacienteCreateRequest) throws Exception {
        Boolean byNome = paciente.existsByNomePaciente(pacienteCreateRequest.getNomePaciente());
        if (byNome) {
            throw new PacienteRegistradoException();
        }
    }

    public void adicionarPaciente(PacienteCreateRequest pacienteCreateRequest) throws Exception{
        verificarPacienteCriado(pacienteCreateRequest);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setStatusPaciente(StatusDoPaciente.AGUARDANDO_TRIAGEM);
        paciente.save(pacienteModel);
    }

    public void atualizarPaciente(Long id, PacienteCreateRequest pacienteCreateRequest) throws Exception{
        verificarPacienteExiste(id);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setIdPaciente(id);
        paciente.save(pacienteModel);
    }

    public void removerPaciente(Long id) throws Exception {
        verificarPacienteExiste(id);
        paciente.deleteById(id);
    }

    public void realizarTriagem(Long id, TriagemPacienteRequest triagemPacienteRequest) throws Exception {
        verificarPacienteExiste(id);
        cadastramentoTriagem(id, triagemPacienteRequest);
        Optional<PacienteModel> pacienteModel = pacienteRepository.findById(id);
        verificarTriagemRealizada(pacienteModel.get());
        pacienteModel.get().setStatusPaciente(StatusDoPaciente.AGUARDANDO_CONSULTA);
        pacienteRepository.save(pacienteModel.get());
    }

    public void verificarTriagemRealizada(PacienteModel pacienteModel) throws Exception{
        if (!pacienteModel.getStatusPaciente().equals(StatusDoPaciente.AGUARDANDO_TRIAGEM)) {
            throw new TriagemStatusException();
        }
    }

    public void realizarConsulta(Long id, ConsultaPacienteRequest consultaPacienteRequest) throws Exception {
        verificarPacienteExiste(id);
        cadastramentoConsulta(id, consultaPacienteRequest);
        Optional<PacienteModel> pacienteModel = pacienteRepository.findById(id);
        verificarConsultaRealizada(pacienteModel.get());
        pacienteModel.get().setStatusPaciente(StatusDoPaciente.CONSULTA_REALIZADA);
        pacienteRepository.save(pacienteModel.get());
    }

    public void verificarConsultaRealizada(PacienteModel pacienteModel) throws Exception {
        if (!pacienteModel.getStatusPaciente().equals(StatusDoPaciente.AGUARDANDO_CONSULTA)) {
            throw new ConsultaStatusException();
        }
    }

    public void cadastramentoTriagem(Long id, TriagemPacienteRequest triagemPacienteRequest) throws Exception {
       Optional<PacienteModel> cadastroTriagem = pacienteRepository.findById(id);

       if (cadastroTriagem.isEmpty()) {
            throw new PacienteNaoEncontradoException(id);
        }

       if (triagemPacienteRequest.getAlergiasPaciente() == null) {
            throw new AlergiaVazioException();
        }
        if (triagemPacienteRequest.getQueixasPaciente() == null) {
            throw new QueixasVazioException();
        }

        cadastroTriagem.get().setAlergiasPaciente(triagemPacienteRequest.getAlergiasPaciente());
        cadastroTriagem.get().setQueixasPaciente(triagemPacienteRequest.getQueixasPaciente());
        pacienteRepository.save(cadastroTriagem.get());
    }

    public void cadastramentoConsulta(Long id, ConsultaPacienteRequest consultaPacienteRequest) throws Exception {
        Optional<PacienteModel> cadastroConsulta = pacienteRepository.findById(id);

        if (cadastroConsulta.isEmpty()) {
            throw new PacienteNaoEncontradoException(id);
        }
        if (consultaPacienteRequest.getDiagnosticoPaciente() == null) {
            throw new DiagnosticoVazioException();
        }
        if (consultaPacienteRequest.getPrescricaoPaciente() == null) {
            throw new PrescricaoVazioException();
        }
        cadastroConsulta.get().setDiagnosticoPaciente(consultaPacienteRequest.getDiagnosticoPaciente());
        cadastroConsulta.get().setPrescricaoPaciente(consultaPacienteRequest.getPrescricaoPaciente());
        pacienteRepository.save(cadastroConsulta.get());
    }

    public Page<FIltroPacienteResponse> filtrarPaciente(Long idPaciente, String nomePaciente,
                                                        LocalDate dataNascimento, String cpfPaciente,
                                                        StatusDoPaciente statusDoPaciente, Pageable pageable) {

        Specification<PacienteModel> specification = PacienteSpecification.filtro(idPaciente, nomePaciente, dataNascimento, cpfPaciente, statusDoPaciente);
        Page<PacienteModel> pacienteModelPage = pacienteRepository.findAll(specification, pageable);

        return pacienteModelPage.map(pacienteModel -> new FIltroPacienteResponse(
                pacienteModel.getIdPaciente(),
                pacienteModel.getNomePaciente(),
                pacienteModel.getDataNascimento(),
                pacienteModel.getCpfPaciente(),
                pacienteModel.getStatusPaciente()
        ));
    }


//TODO Realizar a construção do filtro



}
