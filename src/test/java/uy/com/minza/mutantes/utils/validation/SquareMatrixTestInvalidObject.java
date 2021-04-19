package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;

public class SquareMatrixTestInvalidObject {

    @Getter
    @SquareMatrixConstraint
    String[] dna = {"AB", "A"};

    public SquareMatrixTestInvalidObject() {
    }
}
