package org.vitacare.emergenciaservice.exception;

public class TriagemStatusException extends Exception{

    public TriagemStatusException() {
        super("Paciente não está aguardando triagem");
    }
}
