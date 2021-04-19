package uy.com.minza.mutantes.error.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationExceptionTest {

    @Test
    public void consTest() {
        Assertions.assertThrows(
                ValidationException.class,
                () -> {
                    throw new ValidationException("Error de validación");
                },
                "Error de validación"
        );
    }
}