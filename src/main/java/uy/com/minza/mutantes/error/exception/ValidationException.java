package uy.com.minza.mutantes.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion utilizada cuando alguno de los parámetros de entrada no tiene el formato correcto
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends MutantException {

    private static final boolean LOG_TRACE = false;

    public ValidationException() {
        super(LOG_TRACE);
    }

    public ValidationException(String message) {
        super(message, LOG_TRACE);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause, LOG_TRACE);
    }

    public ValidationException(Throwable cause) {
        super(cause, LOG_TRACE);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
