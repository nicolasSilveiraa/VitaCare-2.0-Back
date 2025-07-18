package org.vitacare.exception;

public class ConsultaStatusException extends Exception{

    public ConsultaStatusException() {
        super("Paciente não está aguardando consulta");
    }
}
