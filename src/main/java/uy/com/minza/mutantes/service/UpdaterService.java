package uy.com.minza.mutantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class UpdaterService {

    /**
     * Servicio que mantiene las estadísticas.
     */
    private final StatsService statsService;

    /**
     * Servicio que mantiene el histórico de ADN revisado.
     */
    private final HistoricADNService historicADNService;

    public UpdaterService(
            @Autowired StatsService statsService,
            @Autowired HistoricADNService historicADNService
    ) {
        this.statsService = statsService;
        this.historicADNService = historicADNService;
    }

    @Async("repoExecutor")
    public Future<Boolean> update(final String[] dna, final boolean result) {
        if (this.historicADNService.save(dna, result)) {
            this.statsService.updateStats(result);
            return new AsyncResult<>(true);
        } else {
            return new AsyncResult<>(false);
        }
    }
}
