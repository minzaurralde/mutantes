package uy.com.minza.mutantes.test;

public class DNAExamples {
    public static final String[] DNA_OK_1 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    public static final String[] DNA_OK_2 = {"ATGGGG", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    public static final String[] DNA_NOT_OK_1 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CACCTA", "TCACTG"};
    public static final String[] DNA_INVALID_ROW_LEN = {"ATGCGA", "CAGTG", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    public static final String[] DNA_INVALID_COL_LEN = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA"};
    public static final String[] DNA_NUMERIC_ENTRY = {"ATGCGA", "CAGTGC", "TT5TGT", "AGAAGG", "CCCCTA", "TCACTG"};
    public static final String[] DNA_LOWERCASE_ENTRY = {"ATGCGA", "CAGTGC", "TTaTGT", "AGAAGG", "CCCCTA"};
    public static final String[] DNA_INVALID_CHAR_ENTRY = {"ATGCGA", "CAGTGC", "TTZTGT", "AGAAGG", "CCCCTA"};
}
