package uy.com.minza.mutantes.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatsResultsDTOTest {

    private StatsResultsDTO dto1;
    private StatsResultsDTO dto2;
    private StatsResultsDTO otherDto;

    @BeforeAll
    public void setup() {
        this.dto1 = new StatsResultsDTO(1, 2, 0.5f);
        this.dto2 = new StatsResultsDTO(1, 4, 0.25f);
        this.otherDto = new StatsResultsDTO(1, 2, 0.5f);
    }

    @Test
    public void builder() {
        Assertions.assertEquals(this.dto1, StatsResultsDTO.builder().countMutantDNA(1).countHumanDNA(2).ratio(0.5f).build());
    }

    @Test
    public void builderToString() {
        Assertions.assertNotNull(StatsResultsDTO.builder().toString());
    }

    @Test
    public void getCountMutantDNA() {
        final StatsResultsDTO dtoLocal = new StatsResultsDTO();
        ReflectionTestUtils.setField(dtoLocal, "countMutantDNA", 10);
        Assertions.assertEquals(10, dtoLocal.getCountMutantDNA());
    }

    @Test
    public void getCountHumanDNA() {
        final StatsResultsDTO dtoLocal = new StatsResultsDTO();
        ReflectionTestUtils.setField(dtoLocal, "countHumanDNA", 15);
        Assertions.assertEquals(15, dtoLocal.getCountHumanDNA());
    }

    @Test
    public void getRatio() {
        final StatsResultsDTO dtoLocal = new StatsResultsDTO();
        ReflectionTestUtils.setField(dtoLocal, "ratio", 0.33f);
        Assertions.assertEquals(0.33f, dtoLocal.getRatio());
    }

    @Test
    public void setCountMutantDNA() {
        final StatsResultsDTO dtoLocal = new StatsResultsDTO();
        dtoLocal.setCountMutantDNA(11);
        Assertions.assertEquals(11, ReflectionTestUtils.getField(dtoLocal, "countMutantDNA"));
    }

    @Test
    public void setCountHumanDNA() {
        final StatsResultsDTO dtoLocal = new StatsResultsDTO();
        dtoLocal.setCountHumanDNA(21);
        Assertions.assertEquals(21, ReflectionTestUtils.getField(dtoLocal, "countHumanDNA"));
    }

    @Test
    public void setRatio() {
        final StatsResultsDTO dtoLocal = new StatsResultsDTO();
        dtoLocal.setRatio(0.41f);
        Assertions.assertEquals(0.41f, ReflectionTestUtils.getField(dtoLocal, "ratio"));
    }

    @Test
    public void equalsSameObject() {
        Assertions.assertTrue(this.dto1.equals(this.dto1));
    }

    @Test
    public void equalsDiffObjectSameFields() {
        Assertions.assertTrue(this.dto1.equals(this.otherDto));
    }

    @Test
    public void equalsDiffObjectDiffFields() {
        Assertions.assertTrue(this.dto1.canEqual(this.dto1));
    }

    @Test
    public void canEqualTest() {
        Assertions.assertTrue(this.dto1.canEqual(this.dto1));
    }

    @Test
    public void hashCodeTest() {
        Assertions.assertEquals(this.dto1.hashCode(), this.otherDto.hashCode());
    }

    @Test
    public void toStringEqualsTest() {
        Assertions.assertEquals(this.dto1.toString(), this.otherDto.toString());
    }

    @Test
    public void toStringNotEqualsTest() {
        Assertions.assertNotEquals(this.dto1.toString(), this.dto2);
    }
}