package uy.com.minza.mutantes.error.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import uy.com.minza.mutantes.error.dto.APIError;
import uy.com.minza.mutantes.error.dto.APIErrorItem;
import uy.com.minza.mutantes.error.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Handler de excepciones
 */
@RestControllerAdvice
public class RestExceptionHandler {

    protected ResponseEntity<?> buildResponseEntity(APIError apiError) {
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<?> unexpectedError(Throwable exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Ocurrió un error inesperado.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> validation(ValidationException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Los datos ingresados no son correctos.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<?> invalidFormat(InvalidFormatException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("La petición está mal formada.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message("El método no está soportado.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<?> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message("El mimetype no está soportado.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<?> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .message("El mimetype no está aceptado.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({MissingPathVariableException.class})
    public ResponseEntity<?> handleMissingPathVariable(MissingPathVariableException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Al menos alguna variable en la ruta no fue ingresada.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({TypeMismatchException.class})
    public ResponseEntity<?> handleTypeMismatch(TypeMismatchException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("El tipo de datos no concuerda.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("La petición está mal formada.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("La petición está mal formada.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<?> handleMissingServletRequestParameter(MissingServletRequestParameterException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("La petición está mal formada.")
                .debugMessage(String.format("El campo %s es requerido pero no fue enviado en la petición", exs.getParameterName()))
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> handleBindException(BindException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("La petición está mal formada.")
                .debugMessage(String.format("Se encontraron %s errores de validación", exs.getErrorCount()))
                .subErrors(exs.getAllErrors().stream()
                        .map(e -> APIErrorItem.builder()
                                .message(e.getDefaultMessage())
                                .debugMessage(String.format("Error encontrado con el objeto %s", e.getObjectName()))
                                .build())
                        .collect(Collectors.toList())
                )
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({ServletRequestBindingException.class})
    public ResponseEntity<?> handleServletRequestBindingException(ServletRequestBindingException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("La petición está mal formada.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException exs) {
        return this.buildResponseEntity(APIError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Recurso no encontrado.")
                .debugMessage(exs.getMessage())
                .timestamp(LocalDateTime.now())
                .build());
    }

}
