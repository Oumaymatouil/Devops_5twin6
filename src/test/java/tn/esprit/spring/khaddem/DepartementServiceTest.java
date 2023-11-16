package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.IDepartementService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DepartementServiceTest {

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private IDepartementService departementService;

    @Test
    public void testRetrieveAllDepartements() {
        // Mocking the behavior of the repository
        when(departementRepository.findAll()).thenReturn(Arrays.asList(
                new Departement(),
                new Departement()
        ));

        // Calling the service method
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assertions
        assertEquals(2, result.size());
    }

    @Test
    public void testAddDepartement() {
        // Creating a new Departement object
        Departement newDepartement = new Departement();
        newDepartement.setIdDepartement(1);
        newDepartement.setNomDepart("Nouveau Département");

        // Mocking the behavior of the repository
        when(departementRepository.save(any(Departement.class))).thenReturn(newDepartement);

        // Calling the service method
        Departement result = departementService.addDepartement(newDepartement);

        // Assertions
        assertEquals(1, result.getIdDepartement());
        assertEquals("Nouveau Département", result.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        // Creating an existing Departement object
        Departement existingDepartement = new Departement();

        // Mocking the behavior of the repository
        when(departementRepository.findById(existingDepartement.getIdDepartement())).thenReturn(Optional.of(existingDepartement));
        when(departementRepository.save(any(Departement.class))).thenReturn(existingDepartement);

        // Calling the service method
        Departement updatedDepartement = new Departement();
        Departement result = departementService.updateDepartement(updatedDepartement);

        // Assertions
        assertEquals("Informatique Modifié", result.getNomDepart());
    }

    @Test
    public void testRetrieveDepartement() {
        // Creating an existing Departement object
        Departement existingDepartement = new Departement();

        // Mocking the behavior of the repository
        when(departementRepository.findById(existingDepartement.getIdDepartement())).thenReturn(Optional.of(existingDepartement));

        // Calling the service method
        Departement result = departementService.retrieveDepartement(1);

        // Assertions
        assertEquals("Informatique", result.getNomDepart());
    }

    @Test
    public void testRetrieveDepartementNotFound() {
        // Mocking the behavior of the repository to return an empty optional
        when(departementRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // Calling the service method and expecting an exception
        assertThrows(NotFoundException.class, () -> departementService.retrieveDepartement(1));
    }

    // Add more test methods for other service methods as needed
}
