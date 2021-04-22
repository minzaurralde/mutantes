package uy.com.minza.mutantes.test;

public class DNAExamples {
    public static final String[][] DNAS_OK = {
            {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"},
            {"ATAAAA", "CAGTGC", "TCAACT", "ACTATT", "GTGGCA", "TCACTG"},
            {"ATGCGA", "CAGTGC", "TTATGT", "AGACGG", "CCCCCA", "TCACTG"},
            {"ATAAAA", "CAGTGC", "TCAACT", "ACTTTT", "GCGGCA", "TCACTG"},
            {"ATGCGA", "CAGTGC", "TTATCT", "AGTTTT", "GCGTCA", "TCACTG"},
            {"AAAAG", "CAATG", "GGCAC", "ACTAA", "GTGGC"},
            {"GATAG", "CAATG", "GACAC", "ACTAA", "GTGGC"},
            {"ATGCGA", "CAGTGC", "TTATCT", "AGACGG", "CCCCAA", "TCACTG"},
            {"GATAC","CAACG","GGCAC","ACTAA","GTGGC"},
            {"GATAC","CAATA","GGCAC","ACATA","GATGC"}
    };
    public static final String[][] DNAS_NOT_OK = {
            {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"},
            {"ATGCGA", "CAGTGC", "TTATCT", "AGACGG", "GCGTCA", "TCACTG"},
            {"ATGCGA", "CAGTGC", "TAATCT", "AAACGG", "GAGTCA", "TCACTG"},
            {"ATGCGA", "CAGTGC", "TTATCT", "AGCATT", "GCGTCA", "TCACTG"},
            {"ATAAGA", "CAGTGC", "TCCACT", "ACTATT", "GTGGCA", "TCCCTG"},
            {"ATCAGA", "CAGTGC", "GGCACT", "ACTAAT", "GTGGCC", "TCCCTG"},
            {"ATCAG", "CAGTG", "GGCAC", "ACTAA", "GTGGC"}
    };
    public static final String[][] DNAS_INVALID = {
            {"ATGCGA", "CAGTG", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"},
            {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA"},
            {"ATGCGA", "CAGTGC", "TT5TGT", "AGAAGG", "CCCCTA", "TCACTG"},
            {"ATGCGA", "CAGTGC", "TTaTGT", "AGAAGG", "CCCCTA"},
            {"ATGCGA", "CAGTGC", "TTZTGT", "AGAAGG", "CCCCTA"}
    };

    public static final String[] DNA_OK_1 = DNAS_OK[0];
    public static final String[] DNA_OK_2 = DNAS_OK[1];
    public static final String[] DNA_NOT_OK_1 = DNAS_NOT_OK[0];
    public static final String[] DNA_INVALID_ROW_LEN = DNAS_INVALID[0];
    public static final String[] DNA_INVALID_COL_LEN = DNAS_INVALID[1];
    public static final String[] DNA_NUMERIC_ENTRY = DNAS_INVALID[2];
    public static final String[] DNA_LOWERCASE_ENTRY = DNAS_INVALID[3];
    public static final String[] DNA_INVALID_CHAR_ENTRY = DNAS_INVALID[4];
}
