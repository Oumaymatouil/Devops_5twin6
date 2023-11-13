package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;
    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService; // Assuming you have a class implementing IUniversiteService

    Departement departement;
    List<Departement> departements;
    Universite universite;
    Universite savedUniversite;
    String notFound="introuvable universite";



    @BeforeEach
    void setUp() {
        departement = Departement.builder().idDepartement(1).nomDepart("TWIN").build();
        departements = new ArrayList<>();
        departements.add(departement);
        universite = new Universite(1, "Universite espirt", departements);

        savedUniversite = universiteRepository.save(universite);
      //  MockitoAnnotations.initMocks(this);

    }

    @Test
    void testRetrieveAllUniversites() {
        // Mocking the repository to return a list of Universites
        List<Universite> universites = new ArrayList<>();
        List<Departement> departementList = new ArrayList<>();
        departementList.add(new Departement());

        universites.add(new Universite(1, "Universite 1", departementList));
        universites.add(new Universite(2, "Universite 2", departementList));
        Mockito.when(universiteRepository.findAll()).thenReturn(universites);

        // Calling the service method
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Asserting the result
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testAddUniversite() {
        // Creating a mock Universite
        List<Departement> departement = new ArrayList<>();
        departement.add(new Departement());
        Universite universite = new Universite(1, "Universite 1", departement);

        // Mocking the repository to return the saved Universite
        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(universite);

        // Calling the service method
        Universite result = universiteService.addUniversite(universite);

        // Asserting the result
        assertNotNull(result);
        assertEquals("Universite 1", result.getNomUniv());
        // assertEquals("Location 1", result.getLocation());
    }

    @Test
    void testUpdateUniversite() {
        // Case where universite.isPresent() is true
        Mockito.when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));
        Universite universite = new Universite(1, "Updated Universite", departements);
        Universite updatedUniversite = universiteService.updateUniversite(universite);
        verify(universiteRepository).save(any());

        assertNotNull(updatedUniversite);
        assertEquals("Updated Universite", updatedUniversite.getNomUniv());

        // Case where universite.isPresent() is false
        Mockito.when(universiteRepository.findById(anyInt())).thenReturn(Optional.empty());
        Universite nonExistentUniversite = new Universite(2, "Non-existent Universite", departements);

        try {
            universiteService.updateUniversite(nonExistentUniversite);
            fail("Expected NotFoundException was not thrown");
        } catch (NotFoundException e) {
            // Expected exception
            assertEquals(notFound, e.getMessage());
        }
    }


    @Test
    void testRetrieveUniversite() {
        // Case where universite.isPresent() is true
        Mockito.when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(universite));
        Integer existingUniversiteId = 1;

        Universite retrievedUniversite = universiteService.retrieveUniversite(existingUniversiteId);

        verify(universiteRepository).findById(existingUniversiteId);
        assertNotNull(retrievedUniversite);
        assertEquals(universite, retrievedUniversite);

        // Case where universite.isPresent() is false
        Mockito.when(universiteRepository.findById(anyInt())).thenReturn(Optional.empty());
        Integer nonExistentUniversiteId = 2;

        try {
            universiteService.retrieveUniversite(nonExistentUniversiteId);
            fail("Expected NotFoundException was not thrown");
        } catch (NotFoundException e) {
            // Expected exception
            assertEquals(notFound, e.getMessage());
        }


    }
    @Test
    void testRemoveUniversite() {
        // Case where universite.isPresent() is true
        Integer existingUniversiteId = 1;
        Mockito.when(universiteRepository.findById(existingUniversiteId)).thenReturn(Optional.of(universite));

        universiteService.removeUniversite(existingUniversiteId);

        verify(universiteRepository).findById(existingUniversiteId);
        verify(universiteRepository).deleteById(existingUniversiteId);

        // Case where universite.isPresent() is false
        Integer nonExistentUniversiteId = 2;
        Mockito.when(universiteRepository.findById(nonExistentUniversiteId)).thenReturn(Optional.empty());

        try {
            universiteService.removeUniversite(nonExistentUniversiteId);
            fail("Expected NotFoundException was not thrown");
        } catch (NotFoundException e) {
            // Expected exception
            assertEquals(notFound, e.getMessage());
        }
    }
    @Test
    void testAssignUniversiteToDepartement() {
        // Case where both universite and departement are present
        Integer existingUniversiteId = 1;
        Integer existingDepartementId = 1;

        Mockito.when(universiteRepository.findById(existingUniversiteId)).thenReturn(Optional.of(universite));
        Mockito.when(departementRepository.findById(existingDepartementId)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(existingUniversiteId, existingDepartementId);

        verify(universiteRepository).findById(existingUniversiteId);
        verify(departementRepository).findById(existingDepartementId);

        // Add your assertions for the actual assignment, for example:
        assertTrue(universite.getDepartements().contains(departement));

        // Case where either universite or departement is not present
        Integer nonExistentUniversiteId = 2;
        Integer nonExistentDepartementId = 2;

        // Test case where universite is not present
        Mockito.when(universiteRepository.findById(nonExistentUniversiteId)).thenReturn(Optional.empty());
        Mockito.when(departementRepository.findById(existingDepartementId)).thenReturn(Optional.of(departement));

        try {
            universiteService.assignUniversiteToDepartement(nonExistentUniversiteId, existingDepartementId);
            fail("Expected NotFoundException was not thrown");
        } catch (NotFoundException e) {
            // Expected exception
            assertEquals(notFound, e.getMessage());
        }

        // Test case where departement is not present
        Mockito.when(universiteRepository.findById(existingUniversiteId)).thenReturn(Optional.of(universite));
        Mockito.when(departementRepository.findById(nonExistentDepartementId)).thenReturn(Optional.empty());

        try {
            universiteService.assignUniversiteToDepartement(existingUniversiteId, nonExistentDepartementId);
            fail("Expected NotFoundException was not thrown");
        } catch (NotFoundException e) {
            // Expected exception
            assertEquals(notFound, e.getMessage());
        }

        // Case where both universite and departement are not present
        Mockito.when(universiteRepository.findById(nonExistentUniversiteId)).thenReturn(Optional.empty());
        Mockito.when(departementRepository.findById(nonExistentDepartementId)).thenReturn(Optional.empty());

        try {
            universiteService.assignUniversiteToDepartement(nonExistentUniversiteId, nonExistentDepartementId);
            fail("Expected NotFoundException was not thrown");
        } catch (NotFoundException e) {
            // Expected exception
            assertEquals(notFound, e.getMessage());
        }
    }



}
