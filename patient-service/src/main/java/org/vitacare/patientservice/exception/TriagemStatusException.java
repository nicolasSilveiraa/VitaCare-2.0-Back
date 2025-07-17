package org.vitacare.patientservice.exception;

public class TriagemStatusException extends Exception{

    public TriagemStatusException() {
        super("Paciente não está aguardando triagem");
    }
}
