package uy.com.minza.mutantes.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HistoricADNService {

    @Async("repoExecutor")
    public void save(String[] dna, boolean result) {
        // TODO save
    }
}
