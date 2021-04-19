package uy.com.minza.mutantes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import uy.com.minza.mutantes.dto.StatsResultsDTO;

@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsServiceTest {

    @Autowired
    @InjectMocks
    private StatsService statsService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateMutantStatsTest() {
        int beforeUpdate = (int) ReflectionTestUtils.getField(StatsService.class, "MUTANTS");
        this.statsService.updateStats(true);
        int afterUpdate = (int) ReflectionTestUtils.getField(StatsService.class, "MUTANTS");
        Assertions.assertEquals(beforeUpdate + 1, afterUpdate);
    }

    @Test
    void updateHumanStatsTest() {
        int beforeUpdate = (int) ReflectionTestUtils.getField(StatsService.class, "HUMANS");
        this.statsService.updateStats(false);
        int afterUpdate = (int) ReflectionTestUtils.getField(StatsService.class, "HUMANS");
        Assertions.assertEquals(beforeUpdate + 1, afterUpdate);
    }

    @Test
    void getStatsOKTest() {
        ReflectionTestUtils.setField(StatsService.class, "HUMANS", 3);
        ReflectionTestUtils.setField(StatsService.class, "MUTANTS", 1);
        final StatsResultsDTO result = this.statsService.getStats();
        Assertions.assertEquals(StatsResultsDTO.builder().ratio(0.25f).countMutantDNA(1).countHumanDNA(3).build(), result);
    }

    @Test
    void getStatsZeroDivisionTest() {
        ReflectionTestUtils.setField(StatsService.class, "HUMANS", 0);
        ReflectionTestUtils.setField(StatsService.class, "MUTANTS", 0);
        final StatsResultsDTO result = this.statsService.getStats();
        Assertions.assertEquals(StatsResultsDTO.builder().ratio(0f).countMutantDNA(0).countHumanDNA(0).build(), result);
    }
}