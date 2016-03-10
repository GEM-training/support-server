package com.gem.support.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vanhop on 3/10/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceInvalidedException extends RuntimeException {

    public ResourceInvalidedException(String message){
        super(message);
    }
}
