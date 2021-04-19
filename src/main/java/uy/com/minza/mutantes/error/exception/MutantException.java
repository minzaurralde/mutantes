package uy.com.minza.mutantes.error.exception;

import lombok.Getter;

/**
 * Excepcion genérica de la aplicacion
 */
@Getter
public abstract class MutantException extends RuntimeException {

    public MutantException(String message) {
        super(message);
    }

}
