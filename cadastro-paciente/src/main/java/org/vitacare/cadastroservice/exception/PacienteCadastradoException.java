package org.vitacare.cadastroservice.exception;

public class PacienteCadastradoException extends Exception{

    public PacienteCadastradoException() {
        super("Paciente já cadastrado");
    }

}
