package uy.com.minza.mutantes.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class SquareMatrixValidator implements ConstraintValidator<SquareMatrixConstraint, String[]> {

    @Override
    public void initialize(SquareMatrixConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        return Arrays.stream(value).noneMatch(row -> value.length != row.length());
    }
}
