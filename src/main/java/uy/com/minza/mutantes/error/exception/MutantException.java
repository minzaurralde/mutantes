package uy.com.minza.mutantes.error.exception;

import lombok.Getter;

/**
 * Excepcion genérica de la aplicacion
 */
@Getter
public abstract class MutantException extends RuntimeException {

    /**
     * Campo que indica si se debe loguear la traza
     */
    protected boolean doLogTrace = false;

    public MutantException(boolean writableStackTrace) {
        this.doLogTrace = writableStackTrace;
    }

    public MutantException(String message, boolean writableStackTrace) {
        super(message);
        this.doLogTrace = writableStackTrace;
    }

    public MutantException(String message, Throwable cause, boolean writableStackTrace) {
        super(message, cause);
        this.doLogTrace = writableStackTrace;
    }

    public MutantException(Throwable cause, boolean writableStackTrace) {
        super(cause);
        this.doLogTrace = writableStackTrace;
    }

    public MutantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.doLogTrace = writableStackTrace;
    }
}
