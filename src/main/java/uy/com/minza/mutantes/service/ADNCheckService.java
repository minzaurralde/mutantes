package uy.com.minza.mutantes.service;

import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;
import uy.com.minza.mutantes.dto.DNADTO;
import uy.com.minza.mutantes.error.exception.ValidationException;
import uy.com.minza.mutantes.service.domain.OverallStatus;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Servicio Spring que realiza verificaciones sobre secuencias de ADNs.
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 * @version 1
 */
@Service
public class ADNCheckService {

    /**
     * Verifica si una secuencia de ADN pertenece a un humano mutante o a un humano no-mutante.
     *
     * @param dna Secuencia de ADN a verificar. Es un array de String, donde cada String representa una fila de una tabla NxN.
     * @return true si la secuencia de ADN pertenece a un humano mutante, false si pertenece a un humano no-mutante.
     */
    @Cacheable("results")
    public boolean isMutant(String[] dna) {
        validate(dna);
        // Constantes en el algoritmo
        int SEQUENCE_MIN_LENGTH = 4; // Largo mínimo de las secuencias a buscar
        int N = dna.length; // Tamaño de la matriz
        // Inicializamos el contador de secuencias de 4 letras
        int sequenceCount = 0;
        // Calculamos la cantidad de diagonales evaluables: si N <= (SECUENCIA_MIN_LEN - 1), no hay diagonales evaluables
        int reviewableDiagonalQty = N >= SEQUENCE_MIN_LENGTH ?
                (N - (SEQUENCE_MIN_LENGTH - 1)) * 2 - 1 :
                0;
        // Inicializamos los contadores y secuencias por diagonal
        int[] diagonalCurrentSeqLengths = new int[reviewableDiagonalQty];
        char[] diagonalPrevChar = new char[reviewableDiagonalQty];
        String[] diagonalValue = new String[reviewableDiagonalQty];
        int[] invertedDiagonalCurrentSeqLengths = new int[reviewableDiagonalQty];
        char[] invertedDiagonalPrevChar = new char[reviewableDiagonalQty];
        String[] invertedDiagonalValue = new String[reviewableDiagonalQty];
        for (int i = 0; i < reviewableDiagonalQty; i++) {
            diagonalCurrentSeqLengths[i] = 1;
            diagonalPrevChar[i] = '0';
            invertedDiagonalCurrentSeqLengths[i] = 1;
            invertedDiagonalPrevChar[i] = '0';
            diagonalValue[i] = "";
            invertedDiagonalValue[i] = "";
        }
        String[] rowValue = new String[N];
        String[] columnValue = new String[N];
        int mainDiagonalIndex = N - SEQUENCE_MIN_LENGTH;
        // Iteramos sobre las filas (y para optimizar, también sobre las columnas en el mismo loop)
        for (int i = 0; i < N; i++) {
            // Inicializamos los contadores por fila y columna
            rowValue[i] = "";
            int rowCurrentSeqLength = 1;
            char rowPrevChar = '0';
            columnValue[i] = "";
            int columnCurrentSeqLength = 1;
            char columnPrevChar = '0';
            // Validamos en la diagonal principal
            char mainDiagonalCurrentChar = dna[i].charAt(i);
            diagonalValue[mainDiagonalIndex] = diagonalValue[mainDiagonalIndex] + mainDiagonalCurrentChar;
            if (mainDiagonalCurrentChar == diagonalPrevChar[mainDiagonalIndex]) {
                diagonalCurrentSeqLengths[mainDiagonalIndex]++;
                if (diagonalCurrentSeqLengths[mainDiagonalIndex] == SEQUENCE_MIN_LENGTH) {
                    sequenceCount++;
                }
            } else {
                diagonalCurrentSeqLengths[mainDiagonalIndex] = 1;
            }
            diagonalPrevChar[mainDiagonalIndex] = mainDiagonalCurrentChar;
            // Validamos en la diagonal principal "inversa"
            char invertedMainDiagonalCurrentChar = dna[i].charAt(N - 1 - i);
            invertedDiagonalValue[mainDiagonalIndex] = invertedDiagonalValue[mainDiagonalIndex] + invertedMainDiagonalCurrentChar;
            if (invertedMainDiagonalCurrentChar == invertedDiagonalPrevChar[mainDiagonalIndex]) {
                invertedDiagonalCurrentSeqLengths[mainDiagonalIndex]++;
                if (invertedDiagonalCurrentSeqLengths[mainDiagonalIndex] == SEQUENCE_MIN_LENGTH) {
                    sequenceCount++;
                }
            } else {
                invertedDiagonalCurrentSeqLengths[mainDiagonalIndex] = 1;
            }
            invertedDiagonalPrevChar[mainDiagonalIndex] = invertedMainDiagonalCurrentChar;
            // Iteramos sobre cada elemento de la fila (o columna)
            for (int j = 0; j < N; j++) {
                // Validamos las diagonales superiores e inferiores (e "inversas")
                if (j > 0 && j < N - (SEQUENCE_MIN_LENGTH - 1)) {
                    int upperDiagonalIndex = N - SEQUENCE_MIN_LENGTH + j;
                    int lowerDiagonalIndex = N - SEQUENCE_MIN_LENGTH - j;
                    if (i + j >= 0 && i + j < N) {
                        // Estamos en la diagonal superior
                        char upperDiagonalCurrentChar = dna[i].charAt(i + j);
                        diagonalValue[upperDiagonalIndex] = diagonalValue[upperDiagonalIndex] + upperDiagonalCurrentChar;
                        if (upperDiagonalCurrentChar == diagonalPrevChar[upperDiagonalIndex]) {
                            diagonalCurrentSeqLengths[upperDiagonalIndex]++;
                            if (diagonalCurrentSeqLengths[upperDiagonalIndex] == SEQUENCE_MIN_LENGTH) {
                                sequenceCount++;
                            }
                        } else {
                            diagonalCurrentSeqLengths[upperDiagonalIndex] = 1;
                        }
                        diagonalPrevChar[upperDiagonalIndex] = upperDiagonalCurrentChar;
                        // Estamos en la diagonal superior "inversa"
                        char invertedUpperDiagonalCurrentChar = dna[i].charAt(N - 1 - i - j);
                        invertedDiagonalValue[upperDiagonalIndex] = invertedDiagonalValue[upperDiagonalIndex] + invertedUpperDiagonalCurrentChar;
                        if (invertedUpperDiagonalCurrentChar == invertedDiagonalPrevChar[upperDiagonalIndex]) {
                            invertedDiagonalCurrentSeqLengths[upperDiagonalIndex]++;
                            if (invertedDiagonalCurrentSeqLengths[upperDiagonalIndex] == SEQUENCE_MIN_LENGTH) {
                                sequenceCount++;
                            }
                        } else {
                            invertedDiagonalCurrentSeqLengths[upperDiagonalIndex] = 1;
                        }
                        invertedDiagonalPrevChar[upperDiagonalIndex] = invertedUpperDiagonalCurrentChar;
                        // Estamos en la diagonal inferior
                        char lowerDiagonalCurrentChar = dna[i + j].charAt(i);
                        diagonalValue[lowerDiagonalIndex] = diagonalValue[lowerDiagonalIndex] + lowerDiagonalCurrentChar;
                        if (lowerDiagonalCurrentChar == diagonalPrevChar[lowerDiagonalIndex]) {
                            diagonalCurrentSeqLengths[lowerDiagonalIndex]++;
                            if (diagonalCurrentSeqLengths[lowerDiagonalIndex] == SEQUENCE_MIN_LENGTH) {
                                sequenceCount++;
                            }
                        } else {
                            diagonalCurrentSeqLengths[lowerDiagonalIndex] = 1;
                        }
                        diagonalPrevChar[lowerDiagonalIndex] = lowerDiagonalCurrentChar;
                        // Estamos en la diagonal inferior "inversa"
                        char invertedLowerDiagonalCurrentChar = dna[i + j].charAt(N - 1 - i);
                        invertedDiagonalValue[lowerDiagonalIndex] = invertedDiagonalValue[lowerDiagonalIndex] + invertedLowerDiagonalCurrentChar;
                        if (invertedLowerDiagonalCurrentChar == invertedDiagonalPrevChar[lowerDiagonalIndex]) {
                            invertedDiagonalCurrentSeqLengths[lowerDiagonalIndex]++;
                            if (invertedDiagonalCurrentSeqLengths[lowerDiagonalIndex] == SEQUENCE_MIN_LENGTH) {
                                sequenceCount++;
                            }
                        } else {
                            invertedDiagonalCurrentSeqLengths[lowerDiagonalIndex] = 1;
                        }
                        invertedDiagonalPrevChar[lowerDiagonalIndex] = invertedLowerDiagonalCurrentChar;
                    } else {
                        diagonalCurrentSeqLengths[upperDiagonalIndex] = 1;
                        diagonalCurrentSeqLengths[lowerDiagonalIndex] = 1;
                        invertedDiagonalCurrentSeqLengths[upperDiagonalIndex] = 1;
                        invertedDiagonalCurrentSeqLengths[lowerDiagonalIndex] = 1;
                    }
                }
                // Validamos la fila
                char rowCurrentChar = dna[i].charAt(j);
                rowValue[i] = rowValue[i] + rowCurrentChar;
                if (rowCurrentChar == rowPrevChar) {
                    rowCurrentSeqLength++;
                    if (rowCurrentSeqLength == SEQUENCE_MIN_LENGTH) {
                        sequenceCount++;
                    }
                } else {
                    rowCurrentSeqLength = 1;
                }
                rowPrevChar = rowCurrentChar;
                // Validamos la columna
                char columnCurrentChar = dna[j].charAt(i);
                columnValue[i] = columnValue[i] + columnCurrentChar;
                if (columnCurrentChar == columnPrevChar) {
                    columnCurrentSeqLength++;
                    if (columnCurrentSeqLength == SEQUENCE_MIN_LENGTH) {
                        sequenceCount++;
                    }
                } else {
                    columnCurrentSeqLength = 1;
                }
                columnPrevChar = columnCurrentChar;
            }
        }
        System.out.println("Sequences: " + sequenceCount);
        System.out.println("Row Values: " + String.join(", ", rowValue));
        System.out.println("Column Values: " + String.join(", ", columnValue));
        System.out.println("Diagonal Values: " + String.join(", ", diagonalValue));
        System.out.println("Inverted Diagonal Values: " + String.join(", ", invertedDiagonalValue));
        // La secuencia de ADN pertenece a un mutante si se encuentra más de una secuencias de 4 letras en la matriz de ADN.
        return sequenceCount > 1;
    }

    public boolean isMutant2(String[] dna) {
        validate(dna);
        // Constantes en el algoritmo
        final OverallStatus overallStatus = OverallStatus.builder().dna(dna).build();
        overallStatus.init();
        return overallStatus.evaluate();
    }

    /**
     * Valida que el DNA tenga las dimensiones correctas y
     *
     * @param dna
     */
    public void validate(final String[] dna) {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<DNADTO>> validationResult = validator.validate(new DNADTO(dna));
        if (!validationResult.isEmpty()) {
            throw new ValidationException("La representación del ADN no es válida: " +
                    validationResult.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining(". "))
            );
        }
    }
}
