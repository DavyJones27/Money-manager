package com.davy.trans.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EtResourcesNotFoundException extends RuntimeException {

    public EtResourcesNotFoundException(String message) {
        super(message);
    }

}

