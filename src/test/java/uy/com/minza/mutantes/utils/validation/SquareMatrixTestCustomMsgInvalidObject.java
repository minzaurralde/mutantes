package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;

public class SquareMatrixTestCustomMsgInvalidObject {

    @Getter
    @SquareMatrixConstraint(message = "El dna se debe representar como una matriz cuadrada.")
    String[] dna = {"AB", "A"};

    public SquareMatrixTestCustomMsgInvalidObject() {
    }
}
