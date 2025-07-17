package org.vitacare.patientservice.exception;

public class PacienteNaoEncontradoException extends Exception{

    public PacienteNaoEncontradoException(Long id) {
        super("Paciente com o id: " + id + " não encontrado");
    }
}
