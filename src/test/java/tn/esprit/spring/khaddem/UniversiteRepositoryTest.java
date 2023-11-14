package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UniversiteRepositoryTest {

    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private DepartementRepository departementRepository;

    Departement departement ;
    List<Departement> departements ;
    Universite universite ;
    Universite savedUniversite ;

    @BeforeEach
    void setUp() {
        departement = Departement.builder().idDepartement(1).nomDepart("TWIN").build();
        departements = new ArrayList<>();
        departements.add(departement);
        universite = new Universite(1, "Universite espirt", departements);
    }


    @Test
    @Order(5)
    void testFindByNomUniv() {
        savedUniversite = universiteRepository.save(universite);

        assertNotNull(savedUniversite);
        assertEquals(universiteRepository.findByNomUniv("Universite espirt"), savedUniversite);
    }
    @Test
    @Order(6)
    void testFindByDepartements() {
        Departement departementF = Departement.builder().idDepartement(4).nomDepart("TWIN2").build();
        ArrayList<Departement> departementsF = new ArrayList<>();
        departementsF.add(departementF);
        Universite universiteF = new Universite(4, "Universite espirt2", departementsF);
        Universite savedUniversiteF = universiteRepository.save(universiteF);

        assertNotNull(savedUniversiteF);
        assertNotNull(universiteRepository.findByDepartements(departementF).getNomUniv());

        assertEquals(universiteRepository.findByDepartements(departementF).getNomUniv(), savedUniversiteF.getNomUniv());
        System.out.println("tesssst "+universiteRepository.findByDepartements(departementF).getNomUniv());
    }

    @Test
    @Order(0)
    void testAddUniversite() {
        savedUniversite = universiteRepository.save(universite);

        assertNotNull(savedUniversite);
        assertEquals("Universite espirt", savedUniversite.getNomUniv());
        assertEquals(departements.size(), savedUniversite.getDepartements().size());
        if (!departements.isEmpty()) {
            for (int i = 0; i < departements.size(); i++)  {
                assertEquals(departements.get(i).getNomDepart(), savedUniversite.getDepartements().get(i).getNomDepart());
            }
        }


    }

    @Test
    @Order(1)
    void testRetrieveUniversite() {
        savedUniversite = universiteRepository.save(universite);

        assertNotNull(savedUniversite);
        Universite foundUniversite = universiteRepository.findById(savedUniversite.getIdUniversite()).orElse(null);

        assertNotNull(foundUniversite);
        assertEquals(savedUniversite.getIdUniversite(), foundUniversite.getIdUniversite());
        assertEquals("Universite espirt", foundUniversite.getNomUniv());
        if (!departements.isEmpty()) {
            for (int i = 0; i < departements.size(); i++)  {
                assertEquals(departements.get(i).getNomDepart(), universite.getDepartements().get(i).getNomDepart());
            }
        }
    }

    @Test
    @Order(2)
    void testUpdateUniversite() {
        savedUniversite = universiteRepository.save(universite);

        assertNotNull(savedUniversite);

        Departement departement2 = Departement.builder().idDepartement(2).nomDepart("TWIN6").build();
        List<Departement> departements2 = new ArrayList<>();
        departements2.add(departement2);

        savedUniversite.setNomUniv("Updated Universite");
        savedUniversite.setDepartements(departements2);

        Universite updatedUniversite = universiteRepository.save(savedUniversite);
        assertNotNull(updatedUniversite);
        assertEquals(savedUniversite.getIdUniversite(), updatedUniversite.getIdUniversite());
        assertEquals("Updated Universite", updatedUniversite.getNomUniv());
        assertEquals(departements2, updatedUniversite.getDepartements());

    }

    @Test
    @Order(4)
    void testRemoveUniversite() {
        savedUniversite = universiteRepository.save(universite);

        assertNotNull(savedUniversite);

        universiteRepository.deleteById(savedUniversite.getIdUniversite());

        Universite deletedUniversite = universiteRepository.findById(savedUniversite.getIdUniversite()).orElse(null);
        assertNull(deletedUniversite);
    }

    @Test
    @Order(3)
    void testRetrieveAllUniversites() {
        List<Universite> universiteList = new ArrayList<>();
        List<Universite> saveAllUniversites =new ArrayList<>();
        universiteList.add(new Universite(1, "Universite 1", departements));
        universiteList.add(new Universite(2, "Universite 2", departements));
        saveAllUniversites = universiteRepository.saveAll(universiteList);


        assertNotNull(saveAllUniversites);
        List<Universite>  foundAllUniversites = universiteRepository.findAll();

        assertNotNull(foundAllUniversites);

        if (!foundAllUniversites.isEmpty()) {
            for (int i = 0; i < foundAllUniversites.size(); i++)  {
                assertEquals(universiteList.get(i).getNomUniv(), foundAllUniversites.get(i).getNomUniv());
            }
        }

    }





}
