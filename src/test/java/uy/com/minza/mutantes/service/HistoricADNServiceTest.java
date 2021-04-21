package uy.com.minza.mutantes.service;

import com.mongodb.MongoException;
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
import uy.com.minza.mutantes.domain.DNA;
import uy.com.minza.mutantes.repository.mongo.DNARepository;
import uy.com.minza.mutantes.test.DNAExamples;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HistoricADNServiceTest {

    @Mock
    private DNARepository dnaRepository;
    @Autowired
    @InjectMocks
    private HistoricADNService historicADNService;

    private DNA dna = DNA.builder().dna(DNAExamples.DNA_OK_1).result(true).build();
    private DNA dnaWithId = DNA.builder().id("1").dna(DNAExamples.DNA_OK_1).result(true).build();

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOKTest() {
        Mockito.clearInvocations(this.dnaRepository);
        Mockito.when(this.dnaRepository.save(Mockito.eq(dna)))
                .thenReturn(dnaWithId);
        Assertions.assertTrue(this.historicADNService.save(DNAExamples.DNA_OK_1, true));
    }

    @Test
    void saveDBErrorTest() {
        Mockito.clearInvocations(this.dnaRepository);
        Mockito.when(this.dnaRepository.save(Mockito.eq(dna)))
                .thenThrow(MongoException.class);
        Assertions.assertFalse(this.historicADNService.save(DNAExamples.DNA_OK_1, true));
    }
}