package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
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
class UniversiteRepositoryTest {

    @Autowired
    private UniversiteRepository universiteRepository;


    Universite universite = Universite.builder()
            .idUniversite(1)
            .nomUniv("Universite")
            .departements(new ArrayList<>(Collections.singletonList(new Departement())))
            .build();


    @Test
    void testSaveUniversite() {
        // Create an instance of Universite and save it
      //  Universite universite = new Universite();
      //  universite.setNomUniv("Universite");
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement());
        universite.setDepartements(departements);

        Universite savedUniversite = universiteRepository.save(universite);

        // Retrieve the saved Universite and assert its properties
        assertNotNull(savedUniversite.getIdUniversite());
        assertEquals("Universite", savedUniversite.getNomUniv());
        assertEquals(departements, savedUniversite.getDepartements());
    }

    @Test
    void testFindUniversiteById() {
        // Save an instance of Universite
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement());
        universite.setDepartements(departements);



        Universite savedUniversite = universiteRepository.save(universite);

        // Find the saved Universite by ID and assert its properties
        Universite foundUniversite = universiteRepository.findById(savedUniversite.getIdUniversite()).orElse(null);

        assertNotNull(foundUniversite);
        assertEquals(savedUniversite.getIdUniversite(), foundUniversite.getIdUniversite());
        assertEquals("Universite", foundUniversite.getNomUniv());
        assertEquals(departements, foundUniversite.getDepartements());
    }

    @Test
    void testUpdateUniversite() {
        // Save an instance of Universite
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement());
        universite.setDepartements(departements);

        Universite savedUniversite = universiteRepository.save(universite);

        // Modify properties of the saved Universite

        savedUniversite.setNomUniv("Updated Universite");
        List<Departement> departements2 = new ArrayList<>();
        departements2.add(new Departement());
        savedUniversite.setDepartements(departements2);

        Universite updatedUniversite = universiteRepository.save(savedUniversite);

        // Retrieve the updated Universite and assert its properties
        assertEquals(savedUniversite.getIdUniversite(), updatedUniversite.getIdUniversite());
        assertEquals("Updated Universite", updatedUniversite.getNomUniv());
        assertEquals(departements2, updatedUniversite.getDepartements());
    }

    @Test
    void testDeleteUniversite() {
        // Save an instance of Universite
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement());
        universite.setDepartements(departements);

        Universite savedUniversite = universiteRepository.save(universite);

        // Delete the saved Universite
        universiteRepository.deleteById(savedUniversite.getIdUniversite());

        // Try to find the deleted Universite and assert it's null
        Universite deletedUniversite = universiteRepository.findById(savedUniversite.getIdUniversite()).orElse(null);
        assertNull(deletedUniversite);
    }
}
