package uy.com.minza.mutantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.com.minza.mutantes.domain.DNA;
import uy.com.minza.mutantes.repository.mongo.DNARepository;

@Service
public class HistoricADNService {

    private DNARepository dnaRepository;

    public HistoricADNService(@Autowired DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public boolean save(String[] dna, boolean result) {
        try {
            final DNA savedObj = this.dnaRepository.save(DNA.builder().dna(dna).result(result).build());
            return savedObj != null;
        } catch (Throwable ignored) {
            return false;
        }
    }
}
