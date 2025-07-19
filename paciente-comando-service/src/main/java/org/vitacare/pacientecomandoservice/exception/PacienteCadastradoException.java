package org.vitacare.pacientecomandoservice.exception;

public class PacienteCadastradoException extends Exception{

    public PacienteCadastradoException() {
        super("Paciente já cadastrado");
    }

}
