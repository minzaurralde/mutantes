package uy.com.minza.mutantes.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class ResourceDeletedException extends MutantException {

    private static final boolean LOG_TRACE = false;

    public ResourceDeletedException() {
        super(LOG_TRACE);
    }

    public ResourceDeletedException(String message) {
        super(message, LOG_TRACE);
    }

    public ResourceDeletedException(String message, Throwable cause) {
        super(message, cause, LOG_TRACE);
    }

    public ResourceDeletedException(Throwable cause) {
        super(cause, LOG_TRACE);
    }

    public ResourceDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
