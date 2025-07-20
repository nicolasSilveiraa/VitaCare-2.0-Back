package org.vitacare.patientservice.exception;

public class PacienteRegistradoException extends Exception{

    public PacienteRegistradoException() {
        super("Paciente já registrado ");
    }

}
