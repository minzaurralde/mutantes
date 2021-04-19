package uy.com.minza.mutantes.error.handler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.NoHandlerFoundException;
import uy.com.minza.mutantes.error.dto.APIError;
import uy.com.minza.mutantes.error.exception.ValidationException;
import uy.com.minza.mutantes.test.TestClass;

class RestExceptionHandlerTest {

    final RestExceptionHandler handler = new RestExceptionHandler();

    @Test
    void buildResponseEntity200Test() {
        final ResponseEntity<?> result = handler.buildResponseEntity(APIError.builder()
                .status(200)
                .message("Error 200")
                .build());
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void buildResponseEntity500Test() {
        final ResponseEntity<?> result = handler.buildResponseEntity(APIError.builder()
                .status(500)
                .message("Error 500")
                .build());
        Assertions.assertEquals(500, result.getStatusCodeValue());
    }

    @Test
    void unexpectedErrorTest() {
        Assertions.assertEquals(500, handler.unexpectedError(new Exception("Error")).getStatusCodeValue());
    }

    @Test
    void validationTest() {
        Assertions.assertEquals(400, handler.validation(new ValidationException("Error")).getStatusCodeValue());
    }

    @Test
    void invalidFormatTest() {
        final JsonParser jsonParser = new TreeTraversingParser(JsonNodeFactory.instance.objectNode());
        Assertions.assertEquals(400, handler.invalidFormat(new InvalidFormatException(jsonParser, "Error", "value", RestExceptionHandlerTest.class)).getStatusCodeValue());
    }

    @Test
    void handleHttpRequestMethodNotSupportedTest() {
        Assertions.assertEquals(405, handler.handleHttpRequestMethodNotSupported(new HttpRequestMethodNotSupportedException("Error")).getStatusCodeValue());
    }

    @Test
    void handleHttpMediaTypeNotSupportedTest() {
        Assertions.assertEquals(415, handler.handleHttpMediaTypeNotSupported(new HttpMediaTypeNotSupportedException("Error")).getStatusCodeValue());
    }

    @Test
    void handleHttpMediaTypeNotAcceptableTest() {
        Assertions.assertEquals(406, handler.handleHttpMediaTypeNotAcceptable(new HttpMediaTypeNotAcceptableException("Error")).getStatusCodeValue());
    }

    @SneakyThrows
    @Test
    void handleMissingPathVariableTest() {
        Assertions.assertEquals(400, handler.handleMissingPathVariable(new MissingPathVariableException("Error", new MethodParameter(TestClass.class.getMethod("test", String.class), 0))).getStatusCodeValue());
    }

    @Test
    void handleTypeMismatchTest() {
        Assertions.assertEquals(400, handler.handleTypeMismatch(new TypeMismatchException("Error", String.class)).getStatusCodeValue());
    }

    @SneakyThrows
    @Test
    void handleMethodArgumentNotValidTest() {
        Assertions.assertEquals(400, handler.handleMethodArgumentNotValid(new MethodArgumentNotValidException(new MethodParameter(TestClass.class.getMethod("test", String.class), 0), new BeanPropertyBindingResult(new TestClass(), "field"))).getStatusCodeValue());
    }

    @Test
    void handleHttpMessageNotReadableTest() {
        Assertions.assertEquals(400, handler.handleHttpMessageNotReadable(new HttpMessageNotReadableException("Error", new MockClientHttpResponse("Hello".getBytes(), HttpStatus.BAD_REQUEST))).getStatusCodeValue());
    }

    @Test
    void handleMissingServletRequestParameterTest() {
        Assertions.assertEquals(400, handler.handleMissingServletRequestParameter(new MissingServletRequestParameterException("field", String.class.getName())).getStatusCodeValue());
    }

    @Test
    void handleBindExceptionTest() {
        Assertions.assertEquals(400, handler.handleBindException(new BindException(new BeanPropertyBindingResult(new TestClass(), "field"))).getStatusCodeValue());
    }

    @Test
    void handleServletRequestBindingExceptionTest() {
        Assertions.assertEquals(400, handler.handleServletRequestBindingException(new ServletRequestBindingException("Error")).getStatusCodeValue());
    }

    @Test
    void handleNoHandlerFoundExceptionTest() {
        Assertions.assertEquals(404, handler.handleNoHandlerFoundException(new NoHandlerFoundException("GET", "/mock", new HttpHeaders())).getStatusCodeValue());
    }
}