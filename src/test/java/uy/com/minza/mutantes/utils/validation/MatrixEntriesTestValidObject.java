package uy.com.minza.mutantes.utils.validation;

import lombok.Getter;

public class MatrixEntriesTestValidObject {

    @Getter
    @MatrixEntriesConstraint(allowedEntries = {'A', 'B', 'C'})
    private String[] dna = {"AB", "BA"};

    public MatrixEntriesTestValidObject() {
    }
}
