package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;
import uy.com.minza.mutantes.MutantsApplication;
import uy.com.minza.mutantes.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class MatrixEntriesValidator implements ConstraintValidator<MatrixEntriesConstraint, String[]> {

    @Getter
    private char[] allowedEntries;
    private StringUtils stringUtils;

    @Override
    public void initialize(MatrixEntriesConstraint constraintAnnotation) {
        this.allowedEntries = constraintAnnotation.allowedEntries();
        this.stringUtils = MutantsApplication.getInstance().getBean(StringUtils.class);
    }

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        return Arrays.stream(value).anyMatch(row -> !this.stringUtils.containsOnly(row, this.allowedEntries));
    }
}
