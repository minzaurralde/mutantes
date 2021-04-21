package uy.com.minza.mutantes.controller;

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
import org.springframework.http.ResponseEntity;
import uy.com.minza.mutantes.dto.StatsResultsDTO;
import uy.com.minza.mutantes.service.StatsService;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatsControllerTest {

    @Mock
    private StatsService statsService;
    @Autowired
    @InjectMocks
    private StatsController statsController;

    private StatsResultsDTO statsResultsDTO = StatsResultsDTO.builder().countHumanDNA(1).countMutantDNA(2).ratio(2 / 3).build();

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getStatsOkTest() {
        Mockito.clearInvocations(this.statsService);
        Mockito.when(this.statsService.getStats())
                .thenReturn(statsResultsDTO);
        final ResponseEntity<StatsResultsDTO> result = this.statsController.getStats();
        Assertions.assertAll(
                () -> Assertions.assertEquals(200, result.getStatusCodeValue()),
                () -> Assertions.assertEquals(statsResultsDTO, result.getBody())
        );
    }
}