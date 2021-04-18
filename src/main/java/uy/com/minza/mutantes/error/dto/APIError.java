package uy.com.minza.mutantes.error.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Información sobre un error
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        description = "Información retornada en caso de error."
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError {

    /**
     * HTTP status de la respuesta
     */
    @JsonProperty("status")
    @ApiModelProperty(
            value = "Estado HTTP de la respuesta.",
            name = "status",
            required = true
    )
    private int status;

    /**
     * Timestamp de la respuesta
     */
    @JsonProperty("timestamp")
    @ApiModelProperty(
            value = "Timestamp de la respuesta.",
            name = "timestamp",
            required = true
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    private LocalDateTime timestamp;

    /**
     * Mensaje de error
     */
    @JsonProperty("message")
    @ApiModelProperty(
            value = "Mensaje asociado al error.",
            name = "message",
            required = true
    )
    private String message;

    /**
     * Mensaje detallado del error
     */
    @JsonProperty("debugMessage")
    @ApiModelProperty(
            value = "Mensaje detallado del error.",
            name = "debugMessage",
            required = false
    )
    private String debugMessage;

    /**
     * Lista de errores
     */
    @JsonProperty("subErrors")
    @ApiModelProperty(
            value = "Lista de errores contenidos dentro de la respuesta en el caso de que ocurra más de uno.",
            name = "subErrors",
            required = false
    )
    private List<APIErrorItem> subErrors = new ArrayList();
}
