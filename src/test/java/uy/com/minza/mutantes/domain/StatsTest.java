package uy.com.minza.mutantes.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatsTest {

    private Stats dto1;
    private Stats dto2;
    private Stats otherDto;

    @BeforeAll
    public void setup() {
        this.dto1 = new Stats("1", 4, 2, 1);
        this.dto2 = new Stats("2", 5, 2, 1);
        this.otherDto = new Stats("1", 4, 2, 1);
    }

    @Test
    void builder() {
        Assertions.assertEquals(this.dto1, Stats.builder().id("1").humanCount(4).mutantCount(2).version(1).build());
    }

    @Test
    void getId() {
        final Stats dtoLocal = new Stats();
        ReflectionTestUtils.setField(dtoLocal, "id", "10");
        Assertions.assertEquals("10", dtoLocal.getId());
    }

    @Test
    void getHumanCount() {
        final Stats dtoLocal = new Stats();
        ReflectionTestUtils.setField(dtoLocal, "humanCount", 10);
        Assertions.assertEquals(10, dtoLocal.getHumanCount());
    }

    @Test
    void getMutantCount() {
        final Stats dtoLocal = new Stats();
        ReflectionTestUtils.setField(dtoLocal, "mutantCount", 10);
        Assertions.assertEquals(10, dtoLocal.getMutantCount());
    }

    @Test
    void getVersion() {
        final Stats dtoLocal = new Stats();
        ReflectionTestUtils.setField(dtoLocal, "version", 1);
        Assertions.assertEquals(1, dtoLocal.getVersion());
    }

    @Test
    void setId() {
        final Stats dtoLocal = new Stats();
        dtoLocal.setId("11");
        Assertions.assertEquals("11", ReflectionTestUtils.getField(dtoLocal, "id"));
    }

    @Test
    void setHumanCount() {
        final Stats dtoLocal = new Stats();
        dtoLocal.setHumanCount(11);
        Assertions.assertEquals(11, ReflectionTestUtils.getField(dtoLocal, "humanCount"));
    }

    @Test
    void setMutantCount() {
        final Stats dtoLocal = new Stats();
        dtoLocal.setMutantCount(11);
        Assertions.assertEquals(11, ReflectionTestUtils.getField(dtoLocal, "mutantCount"));
    }

    @Test
    void setVersion() {
        final Stats dtoLocal = new Stats();
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