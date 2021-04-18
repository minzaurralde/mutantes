package uy.com.minza.mutantes.utils.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

class MatrixEntriesValidatorTest {

    @Test
    void initialize() {
        final MatrixEntriesValidator validator = new MatrixEntriesValidator();
        validator.initialize(MatrixEntriesTestValidObject.class.getAnnotation(MatrixEntriesConstraint.class));
        Assertions.assertArrayEquals(new char[]{'A', 'B', 'C'}, validator.getAllowedEntries());
    }

    @Test
    void isValid() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<MatrixEntriesTestValidObject>> violations = validator.validate(new MatrixEntriesTestValidObject());
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void isValidInvalidTest() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<MatrixEntriesTestInvalidObject>> violations = validator.validate(new MatrixEntriesTestInvalidObject());
        Assertions.assertAll(
                () -> Assertions.assertFalse(violations.isEmpty()),
                () -> Assertions.assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La matriz contiene entradas inválidas.")))
        );
    }

    @Test
    void isValidMessageTest() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<MatrixEntriesTestCustomMsgInvalidObject>> violations = validator.validate(new MatrixEntriesTestCustomMsgInvalidObject());
        Assertions.assertAll(
                () -> Assertions.assertFalse(violations.isEmpty()),
                () -> Assertions.assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("El dna solo puede contener los caracteres A, B o C.")))
        );
    }
}