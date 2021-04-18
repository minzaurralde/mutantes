package uy.com.minza.mutantes.error.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Información de un error detallado
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        description = "Información asociada a un error particular de la respuesta en el caso de que ocurra más de un problema con la ejecución."
)
@Data
@Builder
public class APIErrorItem {

    /**
     * Mensaje del error
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

}
