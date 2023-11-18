package tn.esprit.spring.khaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.khaddem.entities.Contrat;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContratRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Assuming ContratService is mocked or implemented separately for testing

    @Test
    void testGetContrats() throws Exception {
        mockMvc.perform(get("/contrat/retrieve-all-contrats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    void testAddContrat() throws Exception {
        Contrat contrat = new Contrat(); // Create a Contrat object for testing
        mockMvc.perform(post("/contrat/add-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrat)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateContrat() throws Exception {
        Contrat contrat = new Contrat(); // Create a Contrat object for testing
        mockMvc.perform(put("/contrat/update-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrat)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void testGetnbContratsValides() throws Exception {
        Date startDate = new Date(); // Provide valid dates for testing
        Date endDate = new Date();
        mockMvc.perform(get("/contrat/getnbContratsValides/{startDate}/{endDate}", startDate, endDate))
                .andExpect(status().is(400));
    }



    @Test
    void testCalculChiffreAffaireEntreDeuxDates() throws Exception {
        Date startDate = new Date(); // Provide valid dates for testing
        Date endDate = new Date();
        mockMvc.perform(get("/contrat/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}", startDate, endDate))
                .andExpect(status().is(400));
    }
}
