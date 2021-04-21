package uy.com.minza.mutantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uy.com.minza.mutantes.domain.Stats;
import uy.com.minza.mutantes.dto.StatsResultsDTO;
import uy.com.minza.mutantes.repository.StatsRepository;

@Service
public class StatsService {

    static final String ID = "mutant-stats";
    private static final int MAX_RETRIES = 10;

    private StatsRepository statsRepository;

    public StatsService(@Autowired StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Async("statsExecutor")
    public void init() {
        if (!this.statsRepository.existsById(ID)) {
            this.statsRepository.save(Stats.builder().id(ID).humanCount(0).mutantCount(0).build());
        }
    }

    @Async("statsExecutor")
    public void updateStats(boolean result) {
        boolean savedOk = false;
        int retries = 0;
        while (!savedOk && retries < MAX_RETRIES) {
            savedOk = this.statsRepository.addResult(ID, result);
            retries++;
        }
        if (savedOk) {
            this.statsRepository.findById(ID);
        }
    }

    public StatsResultsDTO getStats() {
        final Stats stats = this.statsRepository.findById(ID);
        int humans = stats.getHumanCount();
        int mutants = stats.getMutantCount();
        float ratio = humans + mutants == 0 ? 0f : (float) mutants / ((float) humans + (float) mutants);
        return StatsResultsDTO.builder()
                .countMutantDNA(mutants)
                .countHumanDNA(humans)
                .ratio(ratio)
                .build();
    }
}
