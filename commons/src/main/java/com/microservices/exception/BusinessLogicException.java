package com.microservices.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessLogicException extends RuntimeException {


    private LogLevel errorType;


    public BusinessLogicException() {
        super();
    }


    public BusinessLogicException(String message, LogLevel errorType) {
        super(message);
        this.errorType = errorType;
    }

    public BusinessLogicException(String message, LogLevel errorType, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }


    public BusinessLogicException(Throwable cause) {
        super(cause);
    }


}