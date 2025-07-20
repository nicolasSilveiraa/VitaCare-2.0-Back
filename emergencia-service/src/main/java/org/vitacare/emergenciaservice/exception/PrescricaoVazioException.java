package org.vitacare.emergenciaservice.exception;

public class PrescricaoVazioException extends Exception{

    public PrescricaoVazioException() {
        super("Campo de prescrição é obrigatória");
    }
}
