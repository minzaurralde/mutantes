package uy.com.minza.mutantes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.com.minza.mutantes.utils.validation.MatrixEntriesConstraint;
import uy.com.minza.mutantes.utils.validation.SquareMatrixConstraint;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Clase que representa una secuencia de ADN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
            value = "Secuencia de ADN en formato array de Strings, que representa una matriz de NxN. Cada String solamente puede contener los caracteres A, T, C o G",
            required = true
    )
    @NotNull(message = "El dna no puede ser nulo.")
    @NotEmpty(message = "El dna no puede ser vacío")
    @SquareMatrixConstraint(message = "El dna debe ser representado a través de una matriz cuadrada.")
    @MatrixEntriesConstraint(message = "El dna debe contener únicamente los caracteres A, T, C y G.")
    private String[] dna;

}
