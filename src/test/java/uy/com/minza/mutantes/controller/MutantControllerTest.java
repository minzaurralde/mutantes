package uy.com.minza.mutantes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uy.com.minza.mutantes.dto.DNADTO;
import uy.com.minza.mutantes.test.DNAExamples;

@AutoConfigureMockMvc
@SpringBootTest
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void isMutantOk1Test() {
        isMutant(new DNADTO(DNAExamples.DNA_OK_1), 200);
    }

    @Test
    public void isMutantOk2Test() {
        isMutant(new DNADTO(DNAExamples.DNA_OK_2), 200);
    }

    @Test
    public void isMutantNotOkTest() {
        isMutant(new DNADTO(DNAExamples.DNA_NOT_OK_1), 403);
    }

    @Test
    public void isMutantInvalidColLenTest() {
        isMutant(new DNADTO(DNAExamples.DNA_INVALID_COL_LEN), 400);
    }

    @Test
    public void isMutantInvalidRowLenTest() {
        isMutant(new DNADTO(DNAExamples.DNA_INVALID_ROW_LEN), 400);
    }

    @Test
    public void isMutantInvalidCharTest() {
        isMutant(new DNADTO(DNAExamples.DNA_INVALID_CHAR_ENTRY), 400);
    }

    @SneakyThrows
    public void isMutant(DNADTO dna, int expectedStatus) {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mutant")
                .content(objectMapper.writeValueAsString(dna))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus))
                .andReturn();
    }
}