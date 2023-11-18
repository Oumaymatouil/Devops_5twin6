package tn.esprit.spring.khaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.khaddem.dto.DepartementDTO;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartementRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
     void testRetrieveAllDepartements() throws Exception {
        mockMvc.perform(get("/departement/retrieve-all-departements"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
     void testRetrieveDepartement() throws Exception {
        int departementId = 1; // replace with a valid departement ID
        mockMvc.perform(get("/departement/retrieve-departement/{departement-id}", departementId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
     void testAddDepartement() throws Exception {
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setNomDepart("TestDepartement");
        departementDTO.setEtudiants(Collections.emptyList());

        mockMvc.perform(post("/departement/add-departement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departementDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }



    @Test
     void testRetrieveDepartementsByUniversite() throws Exception {
        int idUniversite = 1; // replace with a valid university ID
        mockMvc.perform(get("/departement/retrieveDepartementsByUniversite/{idUniversite}", idUniversite))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
