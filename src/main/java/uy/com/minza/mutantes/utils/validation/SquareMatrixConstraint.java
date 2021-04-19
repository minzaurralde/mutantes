package uy.com.minza.mutantes.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SquareMatrixValidator.class)
@Target({ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SquareMatrixConstraint {
    String message() default "La matriz debe ser cuadrada.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}
