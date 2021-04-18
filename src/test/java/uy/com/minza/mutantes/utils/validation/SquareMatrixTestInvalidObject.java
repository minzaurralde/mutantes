package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;

public class SquareMatrixTestInvalidObject {

    @Getter
    @SquareMatrixConstraint
    private String[] dna = {"AB", "A"};

    public SquareMatrixTestInvalidObject() {
    }
}
