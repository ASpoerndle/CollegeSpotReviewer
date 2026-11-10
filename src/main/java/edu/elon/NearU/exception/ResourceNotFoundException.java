package edu.elon.NearU.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ResourceNotFoundException.java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String name, String field, Object value) {
        super(String.format("%s not found with %s: '%s'", name, field, value));
    }
}
