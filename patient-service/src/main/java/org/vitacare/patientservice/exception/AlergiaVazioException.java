package org.vitacare.patientservice.exception;

public class AlergiaVazioException extends Exception{

    public AlergiaVazioException() {
        super("Campo de alérgia é obrigatório");
    }

}
