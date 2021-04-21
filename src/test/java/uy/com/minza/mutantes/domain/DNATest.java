package uy.com.minza.mutantes.domain;

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
class DNATest {

    private DNA dto1;
    private DNA dto2;
    private DNA otherDto;

    @BeforeAll
    public void setup() {
        this.dto1 = new DNA("1", DNAExamples.DNA_OK_1, true, 1);
        this.dto2 = new DNA("2", DNAExamples.DNA_OK_2, true, 1);
        this.otherDto = new DNA("1", DNAExamples.DNA_OK_1, true, 1);
    }

    @Test
    void builder() {
        Assertions.assertEquals(this.dto1, DNA.builder().id("1").dna(DNAExamples.DNA_OK_1).result(true).version(1).build());
    }

    @Test
    void getId() {
        final DNA dtoLocal = new DNA();
        ReflectionTestUtils.setField(dtoLocal, "id", "10");
        Assertions.assertEquals("10", dtoLocal.getId());
    }

    @Test
    void getDna() {
        final DNA dtoLocal = new DNA();
        ReflectionTestUtils.setField(dtoLocal, "dna", DNAExamples.DNA_OK_1);
        Assertions.assertEquals(DNAExamples.DNA_OK_1, dtoLocal.getDna());
    }

    @Test
    void getResult() {
        final DNA dtoLocal = new DNA();
        ReflectionTestUtils.setField(dtoLocal, "result", true);
        Assertions.assertEquals(true, dtoLocal.getResult());
    }

    @Test
    void getVersion() {
        final DNA dtoLocal = new DNA();
        ReflectionTestUtils.setField(dtoLocal, "version", 1);
        Assertions.assertEquals(1, dtoLocal.getVersion());
    }

    @Test
    void setId() {
        final DNA dtoLocal = new DNA();
        dtoLocal.setId("11");
        Assertions.assertEquals("11", ReflectionTestUtils.getField(dtoLocal, "id"));
    }

    @Test
    void setDna() {
        final DNA dtoLocal = new DNA();
        dtoLocal.setDna(DNAExamples.DNA_OK_1);
        Assertions.assertEquals(DNAExamples.DNA_OK_1, ReflectionTestUtils.getField(dtoLocal, "dna"));
    }

    @Test
    void setResult() {
        final DNA dtoLocal = new DNA();
        dtoLocal.setResult(true);
        Assertions.assertEquals(true, ReflectionTestUtils.getField(dtoLocal, "result"));
    }

    @Test
    void setVersion() {
        final DNA dtoLocal = new DNA();
        dtoLocal.setVersion(1);
        Assertions.assertEquals(1, ReflectionTestUtils.getField(dtoLocal, "version"));
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