package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ContratServiceTest {

    @Mock
    ContratRepository contratRepository ;

    @InjectMocks
    ContratServiceImpl contratService;

    Contrat c = Contrat.builder().idContrat(1).archived(true).montantContrat(800).dateDebutContrat(new Date()).dateFinContrat(new Date()).specialite(Specialite.RESEAU).build();

    List<Contrat> list= new ArrayList<Contrat>(){
        {
            add(Contrat.builder().idContrat(2).archived(false).montantContrat(1400).dateDebutContrat(new Date()).dateFinContrat(new Date()).specialite(Specialite.IA).build());
            add(Contrat.builder().idContrat(3).archived(true).montantContrat(1200).dateDebutContrat(new Date()).dateFinContrat(new Date()).specialite(Specialite.CLOUD).build());
        }
    };

    @Test
    public void retrieveContratTest(){
        Mockito.when(contratRepository.findById(1)).thenReturn(Optional.of(c));
        Contrat contrat1 = contratService.retrieveContrat(1);
        assertNotNull(contrat1);
        verify(contratRepository).findById(Mockito.anyInt());
    }

    @Test
    public void retrieveAllContratsTest(){
        Mockito.when(contratRepository.findAll()).thenReturn(list);
        List<Contrat> contrats = contratService.retrieveAllContrats();
        assertNotNull(contrats);
        verify(contratRepository).findAll();
    }

    @Test
    public void updateContratTest() {
        // Create a sample Contrat to be updated
        Contrat updatedContrat = Contrat.builder()
                .idContrat(1)
                .archived(true)
                .montantContrat(1000)  // Updated montantContrat value
                .dateDebutContrat(new Date())
                .dateFinContrat(new Date())
                .specialite(Specialite.RESEAU)
                .build();

        // Mock the repository's save method
        Mockito.when(contratRepository.save(updatedContrat)).thenReturn(updatedContrat);

        // Call the updateContrat method
        Contrat updated = contratService.updateContrat(updatedContrat);

        assertNotNull(updated);
        assertEquals(1000, updated.getMontantContrat());
        verify(contratRepository).save(updatedContrat);
    }

    @Test
    public void removeContratTest() {
        int contratId = 1; // ID of the Contrat to be removed

        // Mock the repository's deleteById method
        Mockito.doNothing().when(contratRepository).deleteById(contratId);

        // Call the removeContrat method
        contratService.removeContrat(contratId);

        verify(contratRepository, times(1)).deleteById(contratId);
    }

    @Test
    public void addContratTest() {
        Contrat newContrat = Contrat.builder()
                .idContrat(4)  // A new ID
                .archived(false) // Example values
                .montantContrat(1500)
                .dateDebutContrat(new Date())
                .dateFinContrat(new Date())
                .specialite(Specialite.IA)
                .build();

        // Mock the repository's save method
        Mockito.when(contratRepository.save(newContrat)).thenReturn(newContrat);

        // Call the addContrat method
        Contrat addedContrat = contratService.addContrat(newContrat);

        assertNotNull(addedContrat);
        assertEquals(4, addedContrat.getIdContrat()); // Check if the ID is set correctly
        verify(contratRepository).save(newContrat);
    }


}
