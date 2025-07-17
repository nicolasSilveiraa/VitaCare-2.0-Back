package org.vitacare.agendaservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.vitacare.agendaservice.model.AgendaModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository


public interface AgendaRepository extends JpaRepository<AgendaModel, Long>, JpaSpecificationExecutor<AgendaModel> {
    boolean existsByMedico(String medico);
    boolean existsByEspecialidade(String especialidade);

    List<AgendaModel> findAllByMedico(String medico);

    List<AgendaModel> findAllByEspecialidade(String especialidade);

    boolean existsByMedicoAndDataConsultaAndHoraConsulta(String medico, LocalDate dataConsulta, LocalTime horaConsulta);
}



    //TODO colocar um método buscando por nome(usar contein) vai vir por query

    //TODO criar uma classe Response no dto para fazer a paginação ordenar por registro //(Em andamento)\\
    //TODO permitir no banco criar o mesmo CPF em caso do paciente voltar
    //TODO fazer com que o paciente não possa ser registrado caso o status esteja em "aberto"
    //TODO criar um pacote de Exceptions e erros para tratar
    //TODO alterar o status quando passar de triagem para consulta e quando confirmar depois da consulta dar consulta realizada (X)


