package tn.esprit.spring.khaddem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.khaddem.config.LoggingAspect;
import tn.esprit.spring.khaddem.config.PerformanceAspect;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class DetailEquipeRepositoryTest {
    @Autowired
    private DetailEquipeRepository detailEquipeRepository;



    @Test
    void testSaveDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(1);
        detailEquipe.setThematique("Sample Thematique");
        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(detailEquipe);

        assertNotNull(savedDetailEquipe.getIdDetailEquipe());
    }

    @Test
    void testFindDetailEquipeById() {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(1);
        detailEquipe.setThematique("Sample Thematique");
        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(detailEquipe);

        DetailEquipe foundDetailEquipe = detailEquipeRepository.findById(savedDetailEquipe.getIdDetailEquipe()).orElse(null);
        assertNotNull(foundDetailEquipe);
        assertEquals(savedDetailEquipe.getIdDetailEquipe(), foundDetailEquipe.getIdDetailEquipe());
    }

    @Test
    void testUpdateDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(1);
        detailEquipe.setThematique("Sample Thematique");
        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(detailEquipe);

        DetailEquipe updatedDetailEquipe = detailEquipeRepository.findById(savedDetailEquipe.getIdDetailEquipe()).orElse(null);
        assertNotNull(updatedDetailEquipe);

        updatedDetailEquipe.setSalle(2);
        updatedDetailEquipe.setThematique("Updated Thematique");
        detailEquipeRepository.save(updatedDetailEquipe);

        DetailEquipe foundDetailEquipe = detailEquipeRepository.findById(savedDetailEquipe.getIdDetailEquipe()).orElse(null);
        assertNotNull(foundDetailEquipe);
        assertEquals(updatedDetailEquipe.getSalle(), foundDetailEquipe.getSalle());
        assertEquals(updatedDetailEquipe.getThematique(), foundDetailEquipe.getThematique());
    }

    @Test
    void testDeleteDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setSalle(1);
        detailEquipe.setThematique("Sample Thematique");
        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(detailEquipe);

        detailEquipeRepository.deleteById(savedDetailEquipe.getIdDetailEquipe());
        DetailEquipe foundDetailEquipe = detailEquipeRepository.findById(savedDetailEquipe.getIdDetailEquipe()).orElse(null);
        assertNull(foundDetailEquipe);
    }

    @Test
    void testFindAllDetailEquipes() {
        DetailEquipe detailEquipe1 = new DetailEquipe();
        detailEquipe1.setSalle(1);
        detailEquipe1.setThematique("Thematique 1");
        detailEquipeRepository.save(detailEquipe1);

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setSalle(2);
        detailEquipe2.setThematique("Thematique 2");
        detailEquipeRepository.save(detailEquipe2);

        Iterable<DetailEquipe> allDetailEquipes = detailEquipeRepository.findAll();
        int count = 0;
        for (DetailEquipe detail : allDetailEquipes) {
            count++;
        }
        assertTrue(count >= 2);
    }
}
