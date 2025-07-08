package org.vitacare.exception;

public class PacienteRegistradoException extends Exception{

    public PacienteRegistradoException() {
        super("Paciente já registrado ");
    }

}
