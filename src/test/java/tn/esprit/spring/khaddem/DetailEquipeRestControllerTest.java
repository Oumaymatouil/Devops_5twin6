package tn.esprit.spring.khaddem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.khaddem.dto.DetailEquipeDTO;
import tn.esprit.spring.khaddem.services.IDetailEquipeService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureMockMvc
@SpringBootTest
class DetailEquipeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getDetailEquipes() throws Exception {
        mockMvc.perform(get("/details-equipe/retrieve-all-detail-equipe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getDetailEquipe() throws Exception {
        mockMvc.perform(get("/details-equipe/retrieve-detail-equipe/{detail-equipe-id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addDetailEquipe() throws Exception {
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1);
        detailEquipeDTO.setSalle(1);
        detailEquipeDTO.setThematique("Informatique");

        mockMvc.perform(post("/details-equipe/add-detail-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(detailEquipeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void updateDetailEquipe() throws Exception {
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1);
        detailEquipeDTO.setSalle(2);
        detailEquipeDTO.setThematique("Electronique");

        mockMvc.perform(put("/details-equipe/update-detail-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(detailEquipeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveDetailEquipeByEquipeId() throws Exception {
        mockMvc.perform(get("/details-equipe/retrieve-detail-by-equipe/{equipe-id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void addEquipeToDetailEquipe() throws Exception {
        mockMvc.perform(post("/details-equipe/add-detail-to-equipe/{detail-equipe-id}/{equipe-id}", 1, 2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}