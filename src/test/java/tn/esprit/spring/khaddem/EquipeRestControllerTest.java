package tn.esprit.spring.khaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.khaddem.dto.EquipeDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
 class EquipeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
     void testGetEquipes() throws Exception {
        mockMvc.perform(get("/equipe/retrieve-all-equipes"))
                .andExpect(status().isOk());
    }

    @Test
     void testRetrieveEquipe() throws Exception {
        mockMvc.perform(get("/equipe/retrieve-equipe/{equipe-id}", 1))
                .andExpect(status().isOk());
    }

    @Test
     void testAddEquipe() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();  // Create a valid EquipeDTO for testing
        equipeDTO.setIdEquipe(1);
        equipeDTO.setNomEquipe("Test Equipe");

        mockMvc.perform(post("/equipe/add-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipeDTO)))
                .andExpect(status().isOk());
    }

    @Test
     void testUpdateEquipe() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();  // Create a valid EquipeDTO for testing
        equipeDTO.setIdEquipe(1);
        equipeDTO.setNomEquipe("Updated Equipe");

        mockMvc.perform(put("/equipe/update-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipeDTO)))
                .andExpect(status().isOk());
    }
}
