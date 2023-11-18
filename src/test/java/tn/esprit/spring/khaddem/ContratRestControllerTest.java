package tn.esprit.spring.khaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.khaddem.controllers.ContratDTO;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.services.IContratService;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Collections;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class ContratRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private IContratService contratService;


    @Test
    void testGetContratsa() throws Exception {
        mockMvc.perform(get("/contrat/retrieve-all-contrats"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testAddContrata() throws Exception {
        Contrat contrat = new Contrat(); // Create a Contrat object for testing
        mockMvc.perform(post("/contrat/add-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrat)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateContrat() throws Exception {
        Contrat contrat = new Contrat(); // Create a Contrat object for testing
        mockMvc.perform(put("/contrat/update-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrat)))
                .andExpect(status().isOk());
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

    @Test
    void testGetContrats() throws Exception {
        // Mocking service response
        when(contratService.retrieveAllContrats()).thenReturn(Collections.singletonList(new Contrat()));

        // Perform GET request
        mockMvc.perform(get("/contrat/retrieve-all-contrats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        // Verify that the service method was called
        verify(contratService, times(1)).retrieveAllContrats();
    }

    // Add more tests for other controller methods...

    @Test
    void testAddContrat() throws Exception {
        // Create a ContratDTO for the request body
        ContratDTO contratDTO = new ContratDTO(/* add necessary data */);

        // Mocking service response
        when(contratService.addContrat(any(Contrat.class))).thenReturn(new Contrat());

        // Perform POST request
        mockMvc.perform(post("/contrat/add-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contratDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify that the service method was called with the expected arguments
        verify(contratService, times(1)).addContrat(any(Contrat.class));
    }

    // Add more tests for other controller methods...
}
