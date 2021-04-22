package uy.com.minza.mutantes.service.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 *
 */
@Builder
@Getter
public class OverallStatus {
    @Setter(AccessLevel.PRIVATE)
    private int sequenceCount;
    @Setter(AccessLevel.PRIVATE)
    private EvaluationSectionStatus[] diagonalStatuses;
    @Setter(AccessLevel.PRIVATE)
    private EvaluationSectionStatus[] invertedDiagonalStatuses;
    @Setter(AccessLevel.PRIVATE)
    private EvaluationSectionStatus[] rowStatuses;
    @Setter(AccessLevel.PRIVATE)
    private EvaluationSectionStatus[] columnStatuses;
    @Setter
    private String[] dna;
    @Setter(AccessLevel.PRIVATE)
    private int sequenceMinLength; // Largo mínimo de las secuencias a buscar

    /**
     * Inicializa las estructuras y parámetros generales en base al ADN. Previo a la inicialización, se debe setear el ADN
     */
    public void init() {
        Objects.requireNonNull(this.getDna(), "El adn no puede ser nulo");
        // Incializamos parámetros generales y contadores
        this.setSequenceMinLength(4);
        this.setSequenceCount(0);

        // Inicializamos las estructuras para contabilizar secuencias en las diagonales
        int reviewableDiagonalQty = this.getMatrixSize() >= this.getSequenceMinLength() ?
                (this.getMatrixSize() - (this.getSequenceMinLength() - 1)) * 2 - 1 :
                0;
        this.setDiagonalStatuses(new EvaluationSectionStatus[reviewableDiagonalQty]);
        this.setInvertedDiagonalStatuses(new EvaluationSectionStatus[reviewableDiagonalQty]);
        for (int i = 0; i < reviewableDiagonalQty; i++) {
            this.getDiagonalStatuses()[i] = EvaluationSectionStatus.builder().build().init();
            this.getInvertedDiagonalStatuses()[i] = EvaluationSectionStatus.builder().build().init();
        }

        // Inicializamos las estructuras para contabilizar secuencias en filas y columnas
        this.setRowStatuses(new EvaluationSectionStatus[this.getDna().length]);
        this.setColumnStatuses(new EvaluationSectionStatus[this.getDna().length]);
        for (int i = 0; i < this.getDna().length; i++) {
            this.getRowStatuses()[i] = EvaluationSectionStatus.builder().build().init();
            this.getColumnStatuses()[i] = EvaluationSectionStatus.builder().build().init();
        }
    }

    /**
     * Incrementa el contador de secuencias
     */
    private void incrementSequenceCount() {
        this.setSequenceCount(this.getSequenceCount() + 1);
    }

    /**
     * Verificar una sección en particular en un elemento de la matriz
     *
     * @param sectionStatus Sección a verificar
     * @param row   Indice de fila
     * @param column    Indice de columna
     */
    private void verify(EvaluationSectionStatus sectionStatus, int row, int column) {
        if (sectionStatus.verify(this.getDna(), row, column, this.getSequenceMinLength())) {
            this.incrementSequenceCount();
        }
    }

    /**
     * Obtiene el tamaño de la matriz
     *
     * @return  El tamaño de la matriz de ADN
     */
    private int getMatrixSize() {
        return this.getDna().length;
    }

    /**
     * Evalua el ADN en busca de secuencias
     *
     * @return true si el ADN es mutante, false si es humano
     */
    public boolean evaluate() {
        this.init();
        for (int i = 0; i < this.getMatrixSize(); i++) {
            int mainDiagonalIndex = this.getMatrixSize() - this.getSequenceMinLength();
            this.verify(this.getDiagonalStatuses()[mainDiagonalIndex], i, i);
            this.verify(this.getInvertedDiagonalStatuses()[mainDiagonalIndex], i, this.getMatrixSize() - 1 - i);
            for (int j = 0; j < this.getMatrixSize(); j++) {
                if (j > 0 && j < this.getMatrixSize() - (this.getSequenceMinLength() - 1)) {
                    int upperDiagonalIndex = this.getMatrixSize() - this.getSequenceMinLength() + j;
                    int lowerDiagonalIndex = this.getMatrixSize() - this.getSequenceMinLength() - j;
                    if (i + j >= 0 && i + j < this.getMatrixSize()) {
                        this.verify(this.getDiagonalStatuses()[upperDiagonalIndex], i, i + j);
                        this.verify(this.getInvertedDiagonalStatuses()[upperDiagonalIndex], i, this.getMatrixSize() - 1 - i - j);
                        this.verify(this.getDiagonalStatuses()[lowerDiagonalIndex], i + j, i);
                        this.verify(this.getInvertedDiagonalStatuses()[lowerDiagonalIndex], i + j, this.getMatrixSize() - 1 - i);
                    }
                }
                this.verify(this.getRowStatuses()[i], i, j);
                this.verify(this.getColumnStatuses()[i], j, i);
            }
        }
        return this.getSequenceCount() >= 2;
    }
}

