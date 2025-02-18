package com.microservices.exception;

public class InfraestructureException extends RuntimeException {


    public InfraestructureException() {
        super();
    }


    public InfraestructureException(String message) {
        super(message);
    }

    public InfraestructureException(String message, Throwable cause) {
        super(message, cause);
    }


    public InfraestructureException(Throwable cause) {
        super(cause);
    }
}
