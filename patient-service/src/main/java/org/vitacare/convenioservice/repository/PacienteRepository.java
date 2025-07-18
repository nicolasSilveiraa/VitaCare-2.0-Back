package org.vitacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    Boolean existsByNomePaciente(String nome);

//    Page<PacienteCreateRequest> findFilter(
//
//            @Param("nome") String nomePaciente,
//            @Param("cpf") String cpfPaciente,
//            @Param("id") Long id,
//            @Param("")
//
//    );


    //TODO colocar um método buscando por nome(usar contein) vai vir por query

    //TODO criar uma classe Response no dto para fazer a paginação ordenar por registro
    //TODO permitir no banco criar o mesmo CPF em caso do paciente voltar
    //TODO fazer com que o paciente não possa ser registrado caso o status esteja em "aberto"
    //TODO criar um pacote de Exceptions e erros para tratar
    //TODO alterar o status quando passar de triagem para consulta e quando confirmar depois da consulta dar consulta realizada (X)

}
