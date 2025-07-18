package org.vitacare.convenioservice.exceptions.convenioExceptions;

public class ConvenioNaoExisteException extends Exception{

    public ConvenioNaoExisteException() {
        super("Convênio não existe");
    }

}
