package uy.com.minza.mutantes.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion utilizada cuando alguno de los parámetros de entrada no tiene el formato correcto
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends MutantException {

    public ValidationException(String message) {
        super(message);
    }

}
