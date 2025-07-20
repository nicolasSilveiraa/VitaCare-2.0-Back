package org.vitacare.patientservice.exception;

public class DiagnosticoVazioException extends Exception{

    public DiagnosticoVazioException() {
        super("Campo de diagnóstico é obrigatório");
    }
}
