package uy.com.minza.mutantes.service;

import org.springframework.stereotype.Service;
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
    public boolean isMutant(String[] dna) {
        // Se valida el parámetro adn
        validate(dna);
        // Se inicializa el objeto que va a ejecutar el algoritmo
        final OverallStatus overallStatus = OverallStatus.builder().dna(dna).build();
        // Se ejecuta el algoritmo
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
