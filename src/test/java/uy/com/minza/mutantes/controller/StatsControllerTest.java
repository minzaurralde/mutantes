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
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getStatsOkTest() {
        isMutant(new DNADTO(DNAExamples.DNA_OK_1));
        isMutant(new DNADTO(DNAExamples.DNA_OK_2));
        isMutant(new DNADTO(DNAExamples.DNA_NOT_OK_1));
        getStats(2, 1);
    }

    @SneakyThrows
    public void getStats(int expectedCountMutant, int expectedCountHuman) {
        float expectedRatio = expectedCountHuman + expectedCountMutant == 0 ? 0f : (float) expectedCountMutant / (expectedCountHuman + expectedCountMutant);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/stats")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.count_mutant_dna").value(expectedCountMutant))
                .andExpect(MockMvcResultMatchers.jsonPath("$.count_human_dna").value(expectedCountHuman))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ratio").value(expectedRatio))
                .andReturn();
    }

    @SneakyThrows
    public void isMutant(DNADTO dna) {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mutant")
                .content(objectMapper.writeValueAsString(dna))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}