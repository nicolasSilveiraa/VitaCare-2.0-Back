package org.vitacare.exception;

public class PacienteExisteException extends Exception{

    public PacienteExisteException(Long id) {
        super("Paciente com o ID: " + id + " não encontrado");
    }

}
