package tn.esprit.spring.khaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UniversiteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetUniversites() throws Exception {
        mockMvc.perform(get("/universite/retrieve-all-universites"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRetrieveUniversite() throws Exception {
        mockMvc.perform(get("/universite/retrieve-universite/{universite-id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void testAddUniversite() throws Exception {
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setNomUniv("Test Universite");
        universiteDTO.setDepartements(new ArrayList<>());

        mockMvc.perform(post("/universite/add-universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(universiteDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateUniversite() throws Exception {
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setIdUniversite(1);
        universiteDTO.setNomUniv("Updated Universite");
        universiteDTO.setDepartements(new ArrayList<>());

        mockMvc.perform(put("/universite/update-universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(universiteDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testRemoveUniversite() throws Exception {
        mockMvc.perform(delete("/universite/removeUniversite/{idUniversite}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void testAssignUniversiteToDepartement() throws Exception {
        mockMvc.perform(put("/universite/assignUniversiteToDepartement/{universiteId}/{departementId}", 1, 1))
                .andExpect(status().isOk());
    }
}
