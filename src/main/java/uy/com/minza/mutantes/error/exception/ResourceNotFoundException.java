package uy.com.minza.mutantes.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends MutantException {

    private static final boolean LOG_TRACE = false;

    public ResourceNotFoundException() {
        super(LOG_TRACE);
    }

    public ResourceNotFoundException(String message) {
        super(message, LOG_TRACE);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause, LOG_TRACE);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause, LOG_TRACE);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
