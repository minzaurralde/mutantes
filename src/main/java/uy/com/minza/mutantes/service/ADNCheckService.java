package uy.com.minza.mutantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uy.com.minza.mutantes.error.exception.ValidationException;
import uy.com.minza.mutantes.utils.StringUtils;

import java.util.Arrays;

/**
 * Servicio Spring que realiza verificaciones sobre secuencias de ADNs.
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 * @version 1
 */
@Service
public class ADNCheckService {

    /**
     * Caracteres permitidos en las entradas de ADN
     */
    private final String allowedEntries;
    /**
     * Servicio de utilidades de Strings
     */
    private final StringUtils stringUtils;

    public ADNCheckService(
            @Value("${mutants.allowedEntries:ATCG}") String allowedEntries,
            @Autowired StringUtils stringUtils
    ) {
        this.allowedEntries = allowedEntries;
        this.stringUtils = stringUtils;
    }

    /**
     * Verifica si una secuencia de ADN pertenece a un humano mutante o a un humano no-mutante.
     *
     * @param dna Secuencia de ADN a verificar. Es un array de String, donde cada String representa una fila de una tabla NxN.
     * @return true si la secuencia de ADN pertenece a un humano mutante, false si pertenece a un humano no-mutante.
     */
    public boolean isMutant(String[] dna) {
        validate(dna);
        // Inicializamos el contador de secuencias de 4 letras
        int cantidadSecuencias = 0;
        // Iteramos sobre las filas (y para optimizar, también sobre las columnas en el mismo loop)
        for (int i = 0; i < dna.length; i++) {
            int enFila = 1;
            char anteriorEnFila = '0';
            int enColumna = 1;
            char anteriorEnColumna = '0';
            // Iteramos sobre cada elemento de la fila (o columna)
            for (int j = 0; j < dna.length; j++) {
                // Validamos la fila
                char actualEnFila = dna[i].charAt(j);
                if (actualEnFila == anteriorEnFila) {
                    enFila++;
                    if (enFila == 4) {
                        cantidadSecuencias++;
                    }
                } else {
                    enFila = 1;
                }
                anteriorEnFila = actualEnFila;
                // Validamos la columna
                char actualEnColumna = dna[j].charAt(i);
                if (actualEnColumna == anteriorEnColumna) {
                    enColumna++;
                    if (enColumna == 4) {
                        cantidadSecuencias++;
                    }
                } else {
                    enColumna = 1;
                }
                anteriorEnColumna = actualEnColumna;
            }
        }
        // La secuencia de ADN pertenece a un mutante si se encuentra más de una secuencias de 4 letras en la matriz de ADN.
        return cantidadSecuencias > 1;
    }

    /**
     * Valida que el DNA tenga las dimensiones correctas y
     *
     * @param dna
     */
    public void validate(final String[] dna) {
        // Validamos que el largo de cada fila coincida con la cantidad de filas
        if (Arrays.stream(dna).anyMatch(row -> dna.length != row.length())) {
            throw new ValidationException(String.format("La representación del ADN debe ser una matriz cuadrada de %dx%d", dna.length, dna.length));
        }
        // Validamos que las entradas solo sean las entradas válidas
        if (Arrays.stream(dna).anyMatch(row -> !this.stringUtils.containsOnly(row, this.allowedEntries.toCharArray()))) {
            throw new ValidationException(String.format("Las entradas válidas para los elementos de la matriz de ADN son %s", this.allowedEntries));
        }
    }
}
