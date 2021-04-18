package uy.com.minza.mutantes.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import uy.com.minza.mutantes.test.DNAExamples;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DNADTOTest {

    private DNADTO dto1;
    private DNADTO dto2;
    private DNADTO otherDto;

    @BeforeAll
    public void setup() {
        this.dto1 = new DNADTO(DNAExamples.DNA_OK_1);
        this.dto2 = new DNADTO(DNAExamples.DNA_OK_2);
        this.otherDto = new DNADTO(DNAExamples.DNA_OK_1);
    }

    @Test
    public void getDnaTest() {
        final DNADTO dtoLocal = new DNADTO();
        ReflectionTestUtils.setField(dtoLocal, "dna", DNAExamples.DNA_OK_1);
        Assertions.assertArrayEquals(DNAExamples.DNA_OK_1, dtoLocal.getDna());
    }

    @Test
    public void setDnaTest() {
        final DNADTO dtoLocal = new DNADTO();
        dtoLocal.setDna(DNAExamples.DNA_OK_1);
        Assertions.assertArrayEquals(DNAExamples.DNA_OK_1, (String[]) ReflectionTestUtils.getField(dtoLocal, "dna"));
    }

    @Test
    public void equalsSameObject() {
        Assertions.assertTrue(this.dto1.equals(this.dto1));
    }

    @Test
    public void equalsDiffObjectSameDna() {
        Assertions.assertTrue(this.dto1.equals(this.otherDto));
    }

    @Test
    public void equalsDiffObjectDiffDna() {
        Assertions.assertFalse(this.dto1.equals(this.dto2));
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