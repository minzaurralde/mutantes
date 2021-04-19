package uy.com.minza.mutantes.utils.validation;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uy.com.minza.mutantes.MutantsApplication;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class SquareMatrixValidatorTest {

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    public void setup() {
        MutantsApplication.init(applicationContext);
    }

    @SneakyThrows
    @Test
    void initialize() {
        final SquareMatrixValidator validator = new SquareMatrixValidator();
        validator.initialize(SquareMatrixTestValidObject.class.getDeclaredField("dna").getAnnotation(SquareMatrixConstraint.class));
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