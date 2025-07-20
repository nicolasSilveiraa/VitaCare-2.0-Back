package org.vitacare.agendamentoservice.controller;



import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vitacare.agendamentoservice.AgendamentoServiceApplication;
import org.vitacare.dtos.appointment.AgendaCreateRequest;
import org.vitacare.agendamentoservice.model.AgendaModel;
import org.vitacare.agendamentoservice.service.AgendaService;
import org.vitacare.dtos.appointment.AgendamentoResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/agendamentos")
public class AgendaController {

    private final AgendaService agendaService;

    private static final Logger log = LoggerFactory.getLogger(AgendamentoServiceApplication.class);

    // GET /api/agendamentos
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AgendamentoResponse>> buscarTodasAgendas() {
        return ResponseEntity.ok(agendaService.buscarTodasAgendas());
    }

    // GET /api/agendamentos/{id}
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(agendaService.buscarAgendaPorId(id));
    }

    // POST /api/agendamentos
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AgendamentoResponse> criarAgenda(@RequestBody AgendaCreateRequest request) throws Exception {
        AgendamentoResponse novoAgendamento = agendaService.adicionarAgenda(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }

    // PUT /api/agendamentos/{id}
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> atualizarAgenda(@PathVariable Long id, @RequestBody AgendaCreateRequest request) throws Exception {
        agendaService.atualizarAgenda(id, request);
        return ResponseEntity.ok().build();
    }

    // DELETE /api/agendamentos/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> excluirAgenda(@PathVariable Long id) throws Exception {
        agendaService.excluirAgenda(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/agendamentos/medico/{nome}
    @GetMapping("/medico/{profissionalId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AgendamentoResponse>> buscarPorMedico(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(agendaService.buscarAgendaPorMedico(profissionalId));
    }

    // GET /api/agendamentos/especialidade/{especialidade}
    @GetMapping("/especialidade/{especialidadeId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AgendamentoResponse>> buscarPorEspecialidade(@PathVariable Integer especialidadeId) {
        return ResponseEntity.ok(agendaService.buscarAgendaPorEspecialidade(especialidadeId));
    }
}