package uy.com.minza.mutantes.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uy.com.minza.mutantes.dto.StatsResultsDTO;

@Service
public class StatsService {

    static int HUMANS = 0;
    static int MUTANTS = 0;

    private static synchronized void addResult(boolean result) {
        if (result) {
            MUTANTS++;
        } else {
            HUMANS++;
        }
    }

    @Async("statsExecutor")
    public void updateStats(boolean result) {
        addResult(result);
    }

    public final StatsResultsDTO getStats() {
        int humans = 0;
        int mutants = 0;
        synchronized (this) {
            humans = HUMANS;
            mutants = MUTANTS;
        }
        float ratio = humans + mutants == 0 ? 0f : (float) mutants / ((float) humans + (float) mutants);
        return StatsResultsDTO.builder()
                .countMutantDNA(MUTANTS)
                .countHumanDNA(HUMANS)
                .ratio(ratio)
                .build();
    }
}
