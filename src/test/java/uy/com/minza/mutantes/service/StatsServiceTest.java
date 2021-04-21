package uy.com.minza.mutantes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import uy.com.minza.mutantes.domain.Stats;
import uy.com.minza.mutantes.dto.StatsResultsDTO;
import uy.com.minza.mutantes.repository.StatsRepository;

@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;
    @Autowired
    @InjectMocks
    private StatsService statsService;

    private Stats stats = Stats.builder()
            .mutantCount(3)
            .humanCount(1)
            .id(StatsService.ID)
            .build();

    private Stats emptyStats = Stats.builder()
            .mutantCount(0)
            .humanCount(0)
            .id(StatsService.ID)
            .build();

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.clearInvocations(this.statsRepository);
        Mockito.when(this.statsRepository.existsById(Mockito.eq(StatsService.ID)))
                .thenReturn(true);
        Mockito.when(this.statsRepository.save(Mockito.any(Stats.class)))
                .thenReturn(emptyStats);
        Mockito.when(this.statsRepository.addResult(Mockito.eq(StatsService.ID), Mockito.eq(true)))
                .thenReturn(true);
        Mockito.when(this.statsRepository.addResult(Mockito.eq(StatsService.ID), Mockito.eq(false)))
                .thenReturn(true);
    }

    @Test
    void init() {
        this.statsService.init();
        Assertions.assertTrue(true);
    }

    @Test
    void updateMutantStatsUnitTest() {
        this.statsService.updateStats(true);
        Assertions.assertTrue(true);
    }

    @Test
    void getStatsOKTest() {
        Mockito.when(this.statsRepository.findById(Mockito.eq(StatsService.ID)))
                .thenReturn(stats);
        Assertions.assertEquals(
                StatsResultsDTO.builder()
                        .ratio(stats.getMutantCount() + stats.getHumanCount() == 0 ? 0f : (float) stats.getMutantCount() / (stats.getMutantCount() + stats.getHumanCount()))
                        .countMutantDNA(stats.getMutantCount())
                        .countHumanDNA(stats.getHumanCount())
                        .build(),
                this.statsService.getStats());
    }
}