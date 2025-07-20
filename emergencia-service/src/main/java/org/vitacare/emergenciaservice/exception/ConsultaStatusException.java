package org.vitacare.emergenciaservice.exception;

public class ConsultaStatusException extends Exception{

    public ConsultaStatusException() {
        super("Paciente não está aguardando consulta");
    }
}
