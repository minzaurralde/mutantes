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
import org.springframework.test.util.ReflectionTestUtils;
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
class MatrixEntriesValidatorTest {

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    public void setup() {
        MutantsApplication.init(applicationContext);
    }

    @SneakyThrows
    @Test
    void initialize() {
        final MatrixEntriesValidator validator = new MatrixEntriesValidator();
        validator.initialize(MatrixEntriesTestValidObject.class.getDeclaredField("dna").getAnnotation(MatrixEntriesConstraint.class));
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(new char[]{'A', 'B', 'C'}, (char[]) ReflectionTestUtils.getField(validator, "allowedEntries")),
                () -> Assertions.assertNotNull(ReflectionTestUtils.getField(validator, "stringUtils"))
        );
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