package uy.com.minza.mutantes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * Clase que representa una secuencia de ADN
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        value = "DNA",
        description = "Secuencia de ADN"
)
public class DNADTO {

    /**
     * Secuencia de ADN en formato de array de Strings
     */
    @JsonProperty("dna")
    @ApiModelProperty(
            value = "Secuencia de ADN en formato array de Strings, que representa una matriz de NxN",
            required = true
    )
    @NonNull
    private String[] dna;

}
