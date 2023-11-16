//package tn.esprit.spring.khaddem;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import tn.esprit.spring.khaddem.entities.Contrat;
//import tn.esprit.spring.khaddem.entities.Etudiant;
//import tn.esprit.spring.khaddem.entities.Specialite;
//import tn.esprit.spring.khaddem.repositories.ContratRepository;
//import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
//import tn.esprit.spring.khaddem.services.ContratServiceImpl;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class ContratServiceTest {
//
//    @Mock
//    ContratRepository contratRepository;
//
//    @Mock
//    private EtudiantRepository etudiantRepository;
//
//    @InjectMocks
//    ContratServiceImpl contratService;
//
//    Contrat c = Contrat.builder().idContrat(1).archived(true).montantContrat(800).dateDebutContrat(new Date()).dateFinContrat(new Date()).specialite(Specialite.RESEAU).build();
//
//    List<Contrat> list = new ArrayList<Contrat>() {
//        {
//            add(Contrat.builder().idContrat(2).archived(false).montantContrat(1400).dateDebutContrat(new Date(2023, 9, 1)).dateFinContrat(new Date(2023, 10, 30)).specialite(Specialite.IA).build());
//            add(Contrat.builder().idContrat(3).archived(true).montantContrat(1200).dateDebutContrat(new Date(2023, 9, 1)).dateFinContrat(new Date(2023, 10, 30)).specialite(Specialite.CLOUD).build());
//        }
//    };
//
//    @Test
//    void retrieveContratTest() {
//        Mockito.when(contratRepository.findById(1)).thenReturn(Optional.of(c));
//        Contrat contrat1 = contratService.retrieveContrat(1);
//        assertNotNull(contrat1);
//        verify(contratRepository).findById(Mockito.anyInt());
//    }
//
//    @Test
//    void retrieveAllContratsTest() {
//        Mockito.when(contratRepository.findAll()).thenReturn(list);
//        List<Contrat> contrats = contratService.retrieveAllContrats();
//        assertNotNull(contrats);
//        verify(contratRepository).findAll();
//    }
//
//    @Test
//    void updateContratTest() {
//        // Create a sample Contrat to be updated
//        Contrat updatedContrat = Contrat.builder()
//                .idContrat(1)
//                .archived(true)
//                .montantContrat(1000)  // Updated montantContrat value
//                .dateDebutContrat(new Date())
//                .dateFinContrat(new Date())
//                .specialite(Specialite.RESEAU)
//                .build();
//
//        // Mock the repository's save method
//        Mockito.when(contratRepository.save(updatedContrat)).thenReturn(updatedContrat);
//
//        // Call the updateContrat method
//        Contrat updated = contratService.updateContrat(updatedContrat);
//
//        assertNotNull(updated);
//        assertEquals(1000, updated.getMontantContrat());
//        verify(contratRepository).save(updatedContrat);
//    }
//
//    @Test
//    void removeContratTest() {
//        int contratId = 1; // ID of the Contrat to be removed
//
//        // Mock the repository's deleteById method
//        Mockito.doNothing().when(contratRepository).deleteById(contratId);
//
//        // Call the removeContrat method
//        contratService.removeContrat(contratId);
//
//        verify(contratRepository, times(1)).deleteById(contratId);
//    }
//
//    @Test
//    void addContratTest() {
//        Contrat newContrat = Contrat.builder()
//                .idContrat(4)  // A new ID
//                .archived(false) // Example values
//                .montantContrat(1500)
//                .dateDebutContrat(new Date())
//                .dateFinContrat(new Date())
//                .specialite(Specialite.IA)
//                .build();
//
//        // Mock the repository's save method
//        Mockito.when(contratRepository.save(newContrat)).thenReturn(newContrat);
//
//        // Call the addContrat method
//        Contrat addedContrat = contratService.addContrat(newContrat);
//
//        assertNotNull(addedContrat);
//        assertEquals(4, addedContrat.getIdContrat()); // Check if the ID is set correctly
//        verify(contratRepository).save(newContrat);
//    }
//
//
//    @Test
//    void GetChiffreAffaireEntreDeuxDatesTest() {
//        // Define your test data
//        Date startDate = new Date(2023, 9, 1);
//        Date endDate = new Date(2023, 10, 1);
//
//
//        // Mock the behavior of contratRepository.findAll() to return the list of contrats
//        when(contratRepository.findAll()).thenReturn(list);
//
//        // Call the method to be tested
//        float chiffreAffaire = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
//
//        // Perform assertions to verify the result
//        // Replace these assertions with your expected values
//        assertEquals(2686.66650390625, chiffreAffaire);
//    }
//
//
//    @Test
//    void testAddAndAffectContratToEtudiant_MaximumContractsReached() {
//        // Setup - create a student with 6 active contracts
//        Etudiant etudiant = new Etudiant(); // You might need to create the Etudiant object accordingly
//        when(etudiantRepository.findByNomEAndPrenomE(anyString(), anyString())).thenReturn(etudiant);
//        etudiant.setContrats(Arrays.asList(new Contrat(), new Contrat(), new Contrat(), new Contrat(), new Contrat(), new Contrat()));
//
//        Contrat contrat = new Contrat(); // Create a Contrat object as needed for the test
//
//        // Execution
//        Contrat result = contratService.addAndAffectContratToEtudiant(contrat, "John", "Doe");
//
//        // Assertion
//        // Ensure that the method returns the same contract passed due to maximum contracts reached
//        assertEquals(contrat, result);
//        // You might want to verify that no save operation is invoked on the repository in this scenario.
//        verify(contratRepository, never()).save(any());
//    }
//
//    @Test
//    void testAddAndAffectContratToEtudiant_LessThanMaximumContracts() {
//        // Setup - create a student with 3 active contracts
//        Etudiant etudiant = new Etudiant(); // You might need to create the Etudiant object accordingly
//        when(etudiantRepository.findByNomEAndPrenomE(anyString(), anyString())).thenReturn(etudiant);
//        etudiant.setContrats(Arrays.asList(new Contrat(), new Contrat(), new Contrat()));
//
//        Contrat contrat = new Contrat(); // Create a Contrat object as needed for the test
//
//        // Execution
//        Contrat result = contratService.addAndAffectContratToEtudiant(contrat, "Jane", "Smith");
//
//        // Assertion
//        // Ensure that the method saves the contract and associates it with the student
//        assertEquals(etudiant, contrat.getEtudiant());
//        verify(contratRepository).save(contrat);
//    }
//
//    @Test
//    void retrieveAndUpdateStatusContratTest() {
//        // Setup - Create a contract where the difference in days is 0
//        Date dateNow = new Date();
//
//        Contrat contrat = new Contrat(); // Create a Contrat object and set the required fields
//        contrat.setDateFinContrat(dateNow); // Set the end date to today
//        contrat.setArchived(false); // Make sure it's not archived
//
//        List<Contrat> contrats = Collections.singletonList(contrat);
//
//        when(contratRepository.findAll()).thenReturn(contrats);
//
//        // Execution
//        contratService.retrieveAndUpdateStatusContrat();
//
//        assertTrue(contrat.getArchived());
//    }
//}
