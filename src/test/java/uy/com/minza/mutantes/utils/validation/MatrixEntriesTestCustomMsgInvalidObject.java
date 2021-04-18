package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;

public class MatrixEntriesTestCustomMsgInvalidObject {

    @Getter
    @MatrixEntriesConstraint(allowedEntries = {'A', 'B', 'D'}, message = "El dna solo puede contener los caracteres A, B o C.")
    private String[] dna = {"AB", "BC"};

    public MatrixEntriesTestCustomMsgInvalidObject() {
    }
}
