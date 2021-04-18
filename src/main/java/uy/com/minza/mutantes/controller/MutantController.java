package uy.com.minza.mutantes.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uy.com.minza.mutantes.dto.DNADTO;
import uy.com.minza.mutantes.error.dto.APIError;
import uy.com.minza.mutantes.service.MutantService;

import javax.validation.Valid;

/**
 * Controller de la API REST de mutantes
 */
@Api(value = "Mutants", tags = {"mutantes"})
@RestController
public class MutantController {

    private MutantService mutantService;

    public MutantController(
            @Autowired MutantService mutantService
    ) {
        this.mutantService = mutantService;
    }

    @ApiOperation(
            value = "Valida si una secuencia de ADN pertence a un humano mutante o a uno no mutante.",
            notes = "**Descripción** \n" +
                    "Dada una secuencia de ADN en formato de array de String que representa una matriz de NxN cuyas entradas pueden tener alguno de los caracteres A, T, C, G.\n" +
                    "**Precondiciones**\n" +
                    "Ninguna\n"
            ,
            response = Void.class,
            tags = "mutantes"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "El ADN pertence a un humano mutante", response = Void.class),
            @ApiResponse(code = 400, message = "El ADN ingresado no es válido", response = APIError.class),
            @ApiResponse(code = 403, message = "El ADN pertence a un humano no mutante", response = Void.class),
            @ApiResponse(code = 500, message = "Ocurrio un error al procesar la solicitud", response = APIError.class)
    })
    @RequestMapping(
            value = "/mutant",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<Void> isMutant(
            @Valid @RequestBody DNADTO dna, final BindingResult dnaBindingResult
    ) {
        boolean isMutant = mutantService.isMutant(dna.getDna());
        if (isMutant) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
}
