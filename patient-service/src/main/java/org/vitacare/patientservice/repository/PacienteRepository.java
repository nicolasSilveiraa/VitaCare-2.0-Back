package org.vitacare.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.vitacare.patientservice.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long>, JpaSpecificationExecutor<PacienteModel>{

    Boolean existsByNomePaciente(String nome);

}



    //TODO colocar um método buscando por nome(usar contein) vai vir por query

    //TODO criar uma classe Response no dto para fazer a paginação ordenar por registro //(Em andamento)\\
    //TODO permitir no banco criar o mesmo CPF em caso do paciente voltar
    //TODO fazer com que o paciente não possa ser registrado caso o status esteja em "aberto"
    //TODO criar um pacote de Exceptions e erros para tratar
    //TODO alterar o status quando passar de triagem para consulta e quando confirmar depois da consulta dar consulta realizada (X)


