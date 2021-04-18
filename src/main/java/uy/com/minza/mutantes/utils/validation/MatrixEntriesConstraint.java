package uy.com.minza.mutantes.utils.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatrixEntriesValidator.class)
@Target({ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MatrixEntriesConstraint {
    String message() default "La matriz contiene entradas inválidas.";

    char[] allowedEntries() default {'A', 'T', 'C', 'G'};
}
