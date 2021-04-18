package uy.com.minza.mutantes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa las estadísticas de resultados
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        value = "StatsResults",
        description = "Estadísticas de resultados de las validaciones"
)
public class StatsResultsDTO {

    /**
     * Cantidad de veces que se validó un ADN humano mutante
     */
    @JsonProperty("count_mutant_dna")
    @ApiModelProperty(
            value = "Cantidad de validaciones positivas de un ADN mutante",
            required = true
    )
    private int countMutantDNA;

    /**
     * Cantidad de veces que se validó un ADN humano no mutante
     */
    @JsonProperty("count_human_dna")
    @ApiModelProperty(
            value = "Cantidad de validaciones negativas de un ADN mutante",
            required = true
    )
    private int countHumanDNA;

    /**
     * Cantidad de veces que se validó un ADN humano no mutante
     */
    @JsonProperty("ratio")
    @ApiModelProperty(
            value = "Porcentaje de validaciones positivas de un ADN mutante",
            required = true
    )
    private float ratio;
}
