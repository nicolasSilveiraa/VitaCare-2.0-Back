package org.vitacare.emergenciaservice.exception;

public class PacienteRegistradoException extends Exception{

    public PacienteRegistradoException() {
        super("Paciente já registrado ");
    }

}
