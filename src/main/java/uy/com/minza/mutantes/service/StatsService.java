package uy.com.minza.mutantes.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uy.com.minza.mutantes.dto.StatsResultsDTO;

@Service
public class StatsService {

    @Async("statsExecutor")
    public void updateStats(boolean result) {
        // TODO updateStats
    }

    public StatsResultsDTO getStats() {
        // TODO getStatus
        return StatsResultsDTO.builder()
                .countMutantDNA(0)
                .countHumanDNA(0)
                .ratio(0)
                .build();
    }
}
