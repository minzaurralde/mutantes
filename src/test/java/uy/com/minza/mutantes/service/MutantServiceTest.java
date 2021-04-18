package uy.com.minza.mutantes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uy.com.minza.mutantes.error.exception.ValidationException;

import static uy.com.minza.mutantes.test.DNAExamples.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MutantServiceTest {

    @Mock
    private ADNCheckService adnCheckService;
    @Mock
    private StatsService statsService;
    @Mock
    private HistoricADNService historicADNService;
    @Autowired
    @InjectMocks
    private MutantService mutantService;

    @BeforeAll
    public void setup() {
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_OK_1)))
                .thenReturn(true);
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_OK_2)))
                .thenReturn(true);
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_NOT_OK_1)))
                .thenReturn(false);
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_INVALID_COL_LEN)))
                .thenReturn(false);
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_INVALID_ROW_LEN)))
                .thenReturn(false);
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_INVALID_CHAR_ENTRY)))
                .thenReturn(false);
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_LOWERCASE_ENTRY)))
                .thenReturn(false);
        Mockito.when(this.adnCheckService.isMutant(Mockito.eq(DNA_NUMERIC_ENTRY)))
                .thenReturn(false);
    }

    @Test
    public void isMutantOk1Test() {
        Assertions.assertTrue(this.mutantService.isMutant(DNA_OK_1));
    }

    @Test
    public void isMutantOk2Test() {
        Assertions.assertTrue(this.mutantService.isMutant(DNA_OK_2));
    }

    @Test
    public void isMutantNotOkTest() {
        Assertions.assertFalse(this.mutantService.isMutant(DNA_NOT_OK_1));
    }

    @Test
    public void isMutantInvalidColLengthTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.mutantService.isMutant(DNA_INVALID_COL_LEN);
        });
    }

    @Test
    public void isMutantInvalidRowLengthTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.mutantService.isMutant(DNA_INVALID_ROW_LEN);
        });
    }

    @Test
    public void isMutantNumericEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.mutantService.isMutant(DNA_NUMERIC_ENTRY);
        });
    }

    @Test
    public void isMutantLowerCaseEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.mutantService.isMutant(DNA_LOWERCASE_ENTRY);
        });
    }

    @Test
    public void isMutantInvalidCharEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.mutantService.isMutant(DNA_INVALID_CHAR_ENTRY);
        });
    }
}