package uy.com.minza.mutantes.error.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class APIErrorItemTest {

    private APIErrorItem dto1;
    private APIErrorItem dto2;
    private APIErrorItem otherDto;

    @BeforeAll
    public void setup() {
        this.dto1 = new APIErrorItem("message1", "debugMessage1");
        this.dto2 = new APIErrorItem("message2", "debugMessage2");
        this.otherDto = new APIErrorItem("message1", "debugMessage1");
    }

    @Test
    public void builder() {
        Assertions.assertEquals(this.dto1, APIErrorItem.builder().message("message1").debugMessage("debugMessage1").build());
    }

    @Test
    public void getMessage() {
        final APIErrorItem dtoLocal = new APIErrorItem();
        ReflectionTestUtils.setField(dtoLocal, "message", "message1");
        Assertions.assertEquals("message1", dtoLocal.getMessage());
    }

    @Test
    public void getDebugMessage() {
        final APIErrorItem dtoLocal = new APIErrorItem();
        ReflectionTestUtils.setField(dtoLocal, "debugMessage", "debugMessage1");
        Assertions.assertEquals("debugMessage1", dtoLocal.getDebugMessage());
    }

    @Test
    public void setMessage() {
        final APIErrorItem dtoLocal = new APIErrorItem();
        dtoLocal.setMessage("message4");
        Assertions.assertEquals("message4", ReflectionTestUtils.getField(dtoLocal, "message"));
    }

    @Test
    public void setDebugMessage() {
        final APIErrorItem dtoLocal = new APIErrorItem();
        dtoLocal.setDebugMessage("message5");
        Assertions.assertEquals("message5", ReflectionTestUtils.getField(dtoLocal, "debugMessage"));
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