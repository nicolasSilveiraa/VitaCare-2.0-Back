package org.vitacare.patientservice.exception;

public class QueixasVazioException extends Exception{


    public QueixasVazioException() {
        super("Campo de queixas é obrigatório");
    }
}
