package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService; // Assuming you have a class implementing IUniversiteService

    @Test
    void testRetrieveAllUniversites() {
        // Mocking the repository to return a list of Universites
        List<Universite> universites = new ArrayList<>();
        List<Departement> departement = new ArrayList<>();
        departement.add(new Departement());

        universites.add(new Universite(1, "Universite 1", departement));
        universites.add(new Universite(2, "Universite 2", departement));
        when(universiteRepository.findAll()).thenReturn(universites);

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
        when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(universite);

        // Calling the service method
        Universite result = universiteService.addUniversite(universite);

        // Asserting the result
        assertNotNull(result);
        assertEquals("Universite 1", result.getNomUniv());
       // assertEquals("Location 1", result.getLocation());
    }

    // Similar test methods can be added for update, retrieve, and remove operations

}
