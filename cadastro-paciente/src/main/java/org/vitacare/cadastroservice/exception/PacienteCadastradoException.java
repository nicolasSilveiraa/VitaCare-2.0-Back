package org.vitacare.exception;

public class PacienteCadastradoException extends Exception{

    public PacienteCadastradoException() {
        super("Paciente já cadastrado");
    }

}
