package org.vitacare.exceptions.convenioExceptions;

public class ConvenioNaoExisteException extends Exception{

    public ConvenioNaoExisteException() {
        super("Convênio não existe");
    }

}
