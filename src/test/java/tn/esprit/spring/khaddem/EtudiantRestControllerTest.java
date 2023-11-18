package tn.esprit.spring.khaddem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.khaddem.dto.EtudiantDTO;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
 class EtudiantRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IEtudiantService etudiantService;

    @Test
     void testGetEtudiants() throws Exception {
        // Mocking the service method
        when(etudiantService.retrieveAllEtudiants()).thenReturn(Arrays.asList(new Etudiant(), new Etudiant()));

        // Perform the GET request
        mockMvc.perform(get("/etudiant/retrieve-all-etudiants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        // Verify that the service method was called
        verify(etudiantService, times(1)).retrieveAllEtudiants();
    }

    @Test
     void testRetrieveContrat() throws Exception {
        // Mocking the service method
        when(etudiantService.retrieveEtudiant(anyInt())).thenReturn(new Etudiant());

        // Perform the GET request
        mockMvc.perform(get("/etudiant/retrieve-etudiant/{etudiantId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

        // Verify that the service method was called with the correct parameter
        verify(etudiantService, times(1)).retrieveEtudiant(1);
    }

    // Add similar tests for other methods...

    @Test
     void testAddEtudiant() throws Exception {
        // Mocking the service method
        when(etudiantService.addEtudiant(any(Etudiant.class))).thenReturn(new Etudiant());

        // Prepare the request body
        EtudiantDTO etudiantDTO = new EtudiantDTO();  // Set properties as needed
        String requestBody = objectMapper.writeValueAsString(etudiantDTO);

        // Perform the POST request
        mockMvc.perform(post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

        // Verify that the service method was called with the correct parameter
        verify(etudiantService, times(1)).addEtudiant(any(Etudiant.class));
    }

   @Test
   void getEtudiants() throws Exception {
      mockMvc.perform(get("/etudiant/retrieve-all-etudiants"))
              .andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON));
      // Add more assertions if needed
   }

   @Test
   void addEtudiant() throws Exception {
      EtudiantDTO etudiantDTO = new EtudiantDTO(/* set DTO properties here */);

      mockMvc.perform(post("/etudiant/add-etudiant")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(etudiantDTO)))
              .andExpect(status().isOk());
      // Add more assertions if needed
   }

   @Test
   void retrieveEtudiant() throws Exception {
      mockMvc.perform(get("/etudiant/retrieve-etudiant/{etudiantId}", 1))
              .andExpect(status().isOk());
   }

}
