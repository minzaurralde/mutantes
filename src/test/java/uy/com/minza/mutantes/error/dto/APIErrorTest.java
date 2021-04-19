package uy.com.minza.mutantes.error.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class APIErrorTest {

    private APIError dto1;
    private APIError dto2;
    private APIError dto3;
    private APIError dto4;
    private APIError dto5;
    private APIError dto6;
    private APIError otherDto;
    private LocalDateTime now;

    @BeforeAll
    public void setup() {
        this.now = LocalDateTime.now();
        this.dto1 = new APIError(200, this.now, "message1", "debugMessage1", null);
        this.dto2 = new APIError(500, LocalDateTime.now(), "message2", "debugMessage2", null);
        this.dto3 = new APIError(200, LocalDateTime.now().minus(5, ChronoUnit.MINUTES), "message1", "debugMessage1", null);
        this.dto4 = new APIError(200, this.now, "message2", "debugMessage1", null);
        this.dto5 = new APIError(200, this.now, "message1", "debugMessage2", null);
        this.dto6 = new APIError(200, this.now, "message1", "debugMessage1", Collections.singletonList(APIErrorItem.builder().message("submessage1").build()));
        this.otherDto = new APIError(200, this.now, "message1", "debugMessage1", null);
    }

    @Test
    public void builder() {
        Assertions.assertEquals(this.dto1, APIError.builder().status(200).timestamp(this.now).message("message1").debugMessage("debugMessage1").subErrors(null).build());
    }

    @Test
    public void builderToString() {
        Assertions.assertNotNull(APIError.builder().toString());
    }

    @Test
    public void getStatus() {
        final APIError dtoLocal = new APIError();
        ReflectionTestUtils.setField(dtoLocal, "status", 200);
        Assertions.assertEquals(200, dtoLocal.getStatus());
    }

    @Test
    public void getTimestamp() {
        final APIError dtoLocal = new APIError();
        ReflectionTestUtils.setField(dtoLocal, "timestamp", this.now);
        Assertions.assertEquals(this.now, dtoLocal.getTimestamp());
    }

    @Test
    public void getMessage() {
        final APIError dtoLocal = new APIError();
        ReflectionTestUtils.setField(dtoLocal, "message", "message5");
        Assertions.assertEquals("message5", dtoLocal.getMessage());
    }

    @Test
    public void getDebugMessage() {
        final APIError dtoLocal = new APIError();
        ReflectionTestUtils.setField(dtoLocal, "debugMessage", "message6");
        Assertions.assertEquals("message6", dtoLocal.getDebugMessage());
    }

    @Test
    public void getSubErrors() {
        final APIError dtoLocal = new APIError();
        ReflectionTestUtils.setField(dtoLocal, "subErrors", new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), dtoLocal.getSubErrors());
    }

    @Test
    public void setStatus() {
        final APIError dtoLocal = new APIError();
        dtoLocal.setStatus(201);
        Assertions.assertEquals(201, ReflectionTestUtils.getField(dtoLocal, "status"));
    }

    @Test
    public void setTimestamp() {
        final APIError dtoLocal = new APIError();
        dtoLocal.setTimestamp(this.now);
        Assertions.assertEquals(this.now, ReflectionTestUtils.getField(dtoLocal, "timestamp"));
    }

    @Test
    public void setMessage() {
        final APIError dtoLocal = new APIError();
        dtoLocal.setMessage("message7");
        Assertions.assertEquals("message7", ReflectionTestUtils.getField(dtoLocal, "message"));
    }

    @Test
    public void setDebugMessage() {
        final APIError dtoLocal = new APIError();
        dtoLocal.setDebugMessage("message8");
        Assertions.assertEquals("message8", ReflectionTestUtils.getField(dtoLocal, "debugMessage"));
    }

    @Test
    public void setSubErrors() {
        final APIError dtoLocal = new APIError();
        dtoLocal.setSubErrors(Collections.singletonList(APIErrorItem.builder().message("message10").build()));
        Assertions.assertEquals(Collections.singletonList(APIErrorItem.builder().message("message10").build()), ReflectionTestUtils.getField(dtoLocal, "subErrors"));
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
    public void equalsDiffObjectDiffFields1() {
        Assertions.assertFalse(this.dto1.equals(this.dto2));
    }

    @Test
    public void equalsDiffObjectDiffFields2() {
        Assertions.assertFalse(this.dto1.equals(this.dto3));
    }

    @Test
    public void equalsDiffObjectDiffFields3() {
        Assertions.assertFalse(this.dto1.equals(this.dto4));
    }

    @Test
    public void equalsDiffObjectDiffFields4() {
        Assertions.assertFalse(this.dto1.equals(this.dto5));
    }

    @Test
    public void equalsDiffObjectDiffFields5() {
        Assertions.assertFalse(this.dto1.equals(this.dto6));
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
    public void toStringNotEquals1Test() {
        Assertions.assertNotEquals(this.dto1.toString(), this.dto2);
    }

}