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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.ApplicationContext;
import uy.com.minza.mutantes.MutantsApplication;
import uy.com.minza.mutantes.error.exception.ValidationException;
import uy.com.minza.mutantes.utils.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uy.com.minza.mutantes.test.DNAExamples.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class ADNCheckServiceTest {

    @Mock
    private StringUtils stringUtils;
    @Autowired
    @InjectMocks
    private ADNCheckService adnCheckService;
    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        MutantsApplication.init(this.applicationContext);
        Mockito.clearInvocations(this.stringUtils);
        Stream.concat(Stream.concat(Arrays.stream(DNAS_OK), Arrays.stream(DNAS_NOT_OK)), Arrays.stream(DNAS_INVALID))
                .flatMap(Arrays::stream)
                .filter(s -> s
                        .replaceAll("A", "")
                        .replaceAll("T", "")
                        .replaceAll("C", "")
                        .replaceAll("G", "")
                        .length() == 0
                )
                .collect(Collectors.toSet())
                .forEach(row -> Mockito
                        .when(this.stringUtils.containsOnly(Mockito.eq(row), Mockito.any(char[].class)))
                        .thenReturn(true));
        Stream.concat(Stream.concat(Arrays.stream(DNAS_OK), Arrays.stream(DNAS_NOT_OK)), Arrays.stream(DNAS_INVALID))
                .flatMap(Arrays::stream)
                .filter(s -> s
                        .replaceAll("A", "")
                        .replaceAll("T", "")
                        .replaceAll("C", "")
                        .replaceAll("G", "")
                        .length() > 0
                )
                .collect(Collectors.toSet())
                .forEach(row -> Mockito
                        .when(this.stringUtils.containsOnly(Mockito.eq(row), Mockito.any(char[].class)))
                        .thenReturn(false));
    }

    //region isMutant

    @Test
    public void isMutantOKTest() {
        Assertions.assertArrayEquals(
                Arrays.stream(DNAS_OK).map(dna -> true).toArray(),
                Arrays.stream(DNAS_OK).map(dna -> this.adnCheckService.isMutant(dna)).toArray()
        );
    }

    @Test
    public void isMutantNotOkTest() {
        Assertions.assertArrayEquals(
                Arrays.stream(DNAS_NOT_OK).map(dna -> false).toArray(),
                Arrays.stream(DNAS_NOT_OK).map(dna -> this.adnCheckService.isMutant(dna)).toArray()
        );
    }

    @Test
    public void isMutantInvalidRowLengthTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.isMutant(DNA_INVALID_ROW_LEN);
        });
    }

    @Test
    public void isMutantInvalidColLengthTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.isMutant(DNA_INVALID_COL_LEN);
        });
    }

    @Test
    public void isMutantNumericEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.isMutant(DNA_NUMERIC_ENTRY);
        });
    }

    @Test
    public void isMutantLowerCaseEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.isMutant(DNA_LOWERCASE_ENTRY);
        });
    }

    @Test
    public void isMutantInvalidCharEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.isMutant(DNA_INVALID_CHAR_ENTRY);
        });
    }

    //endregion

    //region validate

    @Test
    public void validateValid1Test() {
        Arrays.stream(DNAS_OK).forEach(dna -> this.adnCheckService.validate(dna));
        Assertions.assertTrue(true);
    }

    @Test
    public void validateValidT2est() {
        Arrays.stream(DNAS_NOT_OK).forEach(dna -> this.adnCheckService.validate(dna));
        Assertions.assertTrue(true);
    }

    @Test
    public void validateInvalidRowLengthTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.validate(DNA_INVALID_ROW_LEN);
        });
    }

    @Test
    public void validateInvalidColumnLengthTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.validate(DNA_INVALID_COL_LEN);
        });
    }

    @Test
    public void validateInvalidNumericEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.validate(DNA_NUMERIC_ENTRY);
        });
    }

    @Test
    public void validateInvalidLowerCaseEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.validate(DNA_LOWERCASE_ENTRY);
        });
    }

    @Test
    public void validateInvalidCharEntryTest() {
        Assertions.assertThrows(ValidationException.class, () -> {
            this.adnCheckService.validate(DNA_INVALID_CHAR_ENTRY);
        });
    }

    //endregion
}