package uy.com.minza.mutantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que trabaja sobre humanos mutantes.
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 */
@Service
public class MutantService {

    /**
     * Servicio que realiza la validación del ADN.
     */
    private final ADNCheckService adnCheckService;

    /**
     * Servicio que realiza la validación del ADN.
     */
    private final UpdaterService updaterService;

    /**
     * Crea una nueva instancia del servicio.
     *
     * @param adnCheckService Servicio que valida el ADN.
     * @param updaterService  Servicio de actualizacion.
     */
    public MutantService(
            @Autowired ADNCheckService adnCheckService,
            @Autowired UpdaterService updaterService
    ) {
        this.adnCheckService = adnCheckService;
        this.updaterService = updaterService;
    }

    /**
     * Verifica si un ADN pertence a un humano mutante o a un humano no mutante.
     *
     * @param dna Array de Strings que contiene el ADN (en forma de matriz de NxN).
     * @return true si el ADN pertenece a un humano mutante, false si pertenece a un humano no mutante.
     */
    public boolean isMutant(
            final String[] dna
    ) {
        final boolean result = this.adnCheckService.isMutant(dna);
        this.updaterService.update(dna, result);
        return result;
    }

}
