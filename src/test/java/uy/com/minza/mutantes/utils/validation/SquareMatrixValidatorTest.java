package uy.com.minza.mutantes.utils.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

class SquareMatrixValidatorTest {

    @Test
    void initialize() {
        final SquareMatrixValidator validator = new SquareMatrixValidator();
        validator.initialize(SquareMatrixTestValidObject.class.getAnnotation(SquareMatrixConstraint.class));
        Assertions.assertTrue(true);
    }

    @Test
    void isValidOkTest() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<SquareMatrixTestValidObject>> violations = validator.validate(new SquareMatrixTestValidObject());
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void isValidInvalidTest() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<SquareMatrixTestInvalidObject>> violations = validator.validate(new SquareMatrixTestInvalidObject());
        Assertions.assertAll(
                () -> Assertions.assertFalse(violations.isEmpty()),
                () -> Assertions.assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La matriz debe ser cuadrada.")))
        );
    }

    @Test
    void isValidMessageTest() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<SquareMatrixTestCustomMsgInvalidObject>> violations = validator.validate(new SquareMatrixTestCustomMsgInvalidObject());
        Assertions.assertAll(
                () -> Assertions.assertFalse(violations.isEmpty()),
                () -> Assertions.assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("El dna se debe representar como una matriz cuadrada.")))
        );
    }
}