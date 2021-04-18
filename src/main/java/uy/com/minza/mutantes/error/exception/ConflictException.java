package uy.com.minza.mutantes.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion utilizada cuando ocurre un conflicto con los datos que se quieren ingresar
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends MutantException {

    private static final boolean LOG_TRACE = true;

    public ConflictException() {
        super(LOG_TRACE);
    }

    public ConflictException(String message) {
        super(message, LOG_TRACE);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause, LOG_TRACE);
    }

    public ConflictException(Throwable cause) {
        super(cause, LOG_TRACE);
    }

    public ConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
