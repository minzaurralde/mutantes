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
     * Servicio que mantiene las estadísticas.
     */
    private final StatsService statsService;

    /**
     * Servicio que mantiene el histórico de ADN revisado.
     */
    private final HistoricADNService historicADNService;

    /**
     * Crea una nueva instancia del servicio.
     *
     * @param adnCheckService    Servicio que valida el ADN.
     * @param statsService       Servicio de estadísticas.
     * @param historicADNService Servicio de almacenamiento de histórico de ADN.
     */
    public MutantService(
            @Autowired ADNCheckService adnCheckService,
            @Autowired StatsService statsService,
            @Autowired HistoricADNService historicADNService
    ) {
        this.adnCheckService = adnCheckService;
        this.statsService = statsService;
        this.historicADNService = historicADNService;
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
        this.statsService.updateStats(result);
        this.historicADNService.save(dna, result);
        return result;
    }

}
