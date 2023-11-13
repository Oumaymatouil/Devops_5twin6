package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UniversiteRepositoryTest {

    @Autowired
    private UniversiteRepository universiteRepository;

    Departement departement = new Departement();
    List<Departement> departements = new ArrayList<>();
    Universite universite =  new Universite();
    @BeforeEach
    void setUp() {
        departement = Departement.builder().idDepartement(1).nomDepart("TWIN").build();
        departements = new ArrayList<>();
        departements.add(departement);

        universite = Universite.builder()
                .idUniversite(1)
                .nomUniv("Universite espirt")
                .departements(departements)
                .build();
    }

    @Test
    void testSaveUniversite() {
        Universite savedUniversite = universiteRepository.save(universite);

        assertNotNull(savedUniversite.getIdUniversite());
        assertEquals("Universite espirt", savedUniversite.getNomUniv());
        assertEquals(departements, savedUniversite.getDepartements());
    }

    @Test
    void testFindUniversiteById() {

        Universite savedUniversite = universiteRepository.save(universite);
        Universite foundUniversite = universiteRepository.findById(savedUniversite.getIdUniversite()).orElse(null);

        assertNotNull(foundUniversite);
        assertEquals(savedUniversite.getIdUniversite(), foundUniversite.getIdUniversite());
        assertEquals("Universite espirt", foundUniversite.getNomUniv());
        assertEquals(departements, foundUniversite.getDepartements());
    }

    @Test
    void testUpdateUniversite() {

        Universite savedUniversite = universiteRepository.save(universite);

        Departement departement2 = Departement.builder().idDepartement(2).nomDepart("TWIN6").build();
        List<Departement> departements2 = new ArrayList<>();
        departements2.add(departement2);

        savedUniversite.setNomUniv("Updated Universite");
        savedUniversite.setDepartements(departements2);

        Universite updatedUniversite = universiteRepository.save(savedUniversite);

        assertEquals(savedUniversite.getIdUniversite(), updatedUniversite.getIdUniversite());
        assertEquals("Updated Universite", updatedUniversite.getNomUniv());
        assertEquals(departements2, updatedUniversite.getDepartements());

    }

    @Test
    void testDeleteUniversite() {

        Universite savedUniversite = universiteRepository.save(universite);

        universiteRepository.deleteById(savedUniversite.getIdUniversite());

        Universite deletedUniversite = universiteRepository.findById(savedUniversite.getIdUniversite()).orElse(null);
        assertNull(deletedUniversite);
    }
}
