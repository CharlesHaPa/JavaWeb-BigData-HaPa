package com.sw1408.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Service Unavailable")

public class MyTestException extends RuntimeException {

    private static final long serialVersionUID = -2776055426513273775L;

    public MyTestException() {
        super("MyTestException message");
    }

    public MyTestException(Throwable t) {
        super("MyTestException message", t);
    }
}