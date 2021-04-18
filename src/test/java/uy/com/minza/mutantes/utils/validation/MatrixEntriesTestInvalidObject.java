package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;

public class MatrixEntriesTestInvalidObject {

    @Getter
    @MatrixEntriesConstraint(allowedEntries = {'A', 'B', 'C'})
    private String[] dna = {"AB", "BC"};

    public MatrixEntriesTestInvalidObject() {
    }
}
