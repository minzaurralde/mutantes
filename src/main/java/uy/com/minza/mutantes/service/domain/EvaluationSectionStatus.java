package uy.com.minza.mutantes.service.domain;

import lombok.*;

@Builder
@Getter
@ToString
class EvaluationSectionStatus {
    @Setter(AccessLevel.PRIVATE)
    private char previousChar;
    @Setter(AccessLevel.PRIVATE)
    private int currentSequenceLength;
    @Setter(AccessLevel.PRIVATE)
    private String sectionValue;

    EvaluationSectionStatus init() {
        this.reset();
        this.setPreviousChar('0');
        this.sectionValue = "";
        return this;
    }

    private void reset() {
        this.setCurrentSequenceLength(1);
    }

    boolean verify(final String[] dna, final int row, final int column, final int sequenceMinLength) {
        final char actual = dna[row].charAt(column);
        boolean addedNewSequence = false;
        if (this.getPreviousChar() == actual) {
            this.setCurrentSequenceLength(this.getCurrentSequenceLength() + 1);
            if (this.getCurrentSequenceLength() == sequenceMinLength) {
                addedNewSequence = true;
            }
        } else {
            reset();
        }
        this.setSectionValue(this.getSectionValue() + actual);
        this.setPreviousChar(actual);
        return addedNewSequence;
    }
}
