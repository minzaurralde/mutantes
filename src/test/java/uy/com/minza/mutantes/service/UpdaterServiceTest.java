package uy.com.minza.mutantes.service;

import lombok.SneakyThrows;
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

@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpdaterServiceTest {

    final String[] DNA_1 = {"AB", "BA"};
    final String[] DNA_2 = {"AC", "BA"};
    @Mock
    private HistoricADNService historicADNService;
    @Mock
    private StatsService statsService;
    @Autowired
    @InjectMocks
    private UpdaterService updaterService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.clearInvocations(this.historicADNService, this.statsService);
        Mockito.when(historicADNService.save(Mockito.eq(DNA_1), Mockito.eq(true)))
                .thenReturn(true);
        Mockito.lenient().when(historicADNService.save(Mockito.eq(DNA_2), Mockito.eq(false)))
                .thenReturn(false);
    }

    @SneakyThrows
    @Test
    void updateCouldSaveTest() {
        Assertions.assertTrue(this.updaterService.update(DNA_1, true).get());
    }

    @SneakyThrows
    @Test
    void updateCouldNotSaveTest() {
        Assertions.assertFalse(this.updaterService.update(DNA_2, false).get());
    }
}
