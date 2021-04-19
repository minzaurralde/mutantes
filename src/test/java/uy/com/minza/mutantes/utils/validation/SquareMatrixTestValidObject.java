package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;

public class SquareMatrixTestValidObject {

    @Getter
    @SquareMatrixConstraint
    String[] dna = {"AB", "BA"};

    public SquareMatrixTestValidObject() {
    }
}
