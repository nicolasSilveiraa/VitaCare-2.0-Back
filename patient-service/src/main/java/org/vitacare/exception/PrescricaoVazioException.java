package org.vitacare.exception;

public class PrescricaoVazioException extends Exception{

    public PrescricaoVazioException() {
        super("Campo de prescrição é obrigatória");
    }
}
