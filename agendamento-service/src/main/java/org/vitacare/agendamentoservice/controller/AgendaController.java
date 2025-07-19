package org.vitacare.agendaservice.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.appointment.AgendaCreateRequest;
import org.vitacare.agendaservice.model.AgendaModel;
import org.vitacare.agendaservice.service.AgendaService;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/agendamentos")
public class AgendaController {

    private final AgendaService agendaService;

    // GET /api/agendamentos
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AgendaModel>> buscarTodasAgendas() {
        return ResponseEntity.ok(agendaService.buscarAgenda());
    }

    // GET /api/agendamentos/{id}
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AgendaModel> buscarPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(agendaService.buscarAgendaPorId(id));
    }

    // POST /api/agendamentos
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> criarAgenda(@RequestBody AgendaCreateRequest request) throws Exception {
        agendaService.adicionarAgenda(request);
        return ResponseEntity.ok().build();
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
    @GetMapping("/medico/{nome}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AgendaModel>> buscarPorMedico(@PathVariable String nome) {
        return ResponseEntity.ok(agendaService.buscarAgendaPorMedico(nome));
    }

    // GET /api/agendamentos/especialidade/{especialidade}
    @GetMapping("/especialidade/{especialidade}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AgendaModel>> buscarPorEspecialidade(@PathVariable String especialidade) {
        return ResponseEntity.ok(agendaService.buscarAgendaPorEspecialidade(especialidade));
    }
}