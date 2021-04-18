package uy.com.minza.mutantes.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uy.com.minza.mutantes.dto.StatsResultsDTO;
import uy.com.minza.mutantes.error.dto.APIError;
import uy.com.minza.mutantes.service.StatsService;

/**
 * Controller de la API REST de estadísticas
 */
@Api(value = "Estadísticas", tags = {"stats"})
@RestController
public class StatsController {

    private StatsService statsService;

    public StatsController(
            @Autowired StatsService statsService
    ) {
        this.statsService = statsService;
    }

    @ApiOperation(
            value = "Retorna las estadísticas de los resultados obtenidos de las validaciones.",
            notes = "**Descripción** \n" +
                    "Obtiene las estadísticas de los resultados obtenidos de las validaciones realizadas de ADN.\n" +
                    "**Precondiciones**\n" +
                    "Ninguna\n"
            ,
            response = StatsResultsDTO.class,
            tags = "stats"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtuvieron correctamente las estadísticas", response = StatsResultsDTO.class),
            @ApiResponse(code = 500, message = "Ocurrio un error al procesar la solicitud", response = APIError.class)
    })
    @RequestMapping(
            value = "/stats",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<StatsResultsDTO> getStats() {
        final StatsResultsDTO stats = this.statsService.getStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
