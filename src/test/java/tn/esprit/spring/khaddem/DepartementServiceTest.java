package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class DepartementServiceTest {

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository  universiteRepository ;

    private DepartementServiceImpl departementService;

    @BeforeEach
    public void setUp() {
        departementService = new DepartementServiceImpl(departementRepository , universiteRepository);
    }

    @Test
    void retrieveAllDepartements() {
        List<Departement> expectedDepartements = new ArrayList<>();
        Mockito.when(departementRepository.findAll()).thenReturn(expectedDepartements);
        List<Departement> result = departementService.retrieveAllDepartements();
        assertEquals(expectedDepartements.size(), result.size());
    }

    @Test
    void addDepartementTest() {
        Departement departement = new Departement();
        Mockito.when(departementRepository.save(departement)).thenReturn(departement);
        Departement result = departementService.addDepartement(departement);
        assertEquals(departement, result);
    }

    @Test
    void retrieveDepartementTest() {
        Integer idDepartement = 1; // Change to the desired ID
        Departement expectedDepartement = new Departement();
        Mockito.when(departementRepository.findById(idDepartement)).thenReturn(Optional.of(expectedDepartement));
        Departement result = departementService.retrieveDepartement(idDepartement);
        assertEquals(expectedDepartement, result);
    }
    @Test
    void testUpdateDepartement() {
        // Créez un département avec les détails existants
        Departement existingDepartement = new Departement();
        existingDepartement.setIdDepartement(1);
        existingDepartement.setNomDepart("Informatique");

        // Créez un département mis à jour avec de nouvelles informations
        Departement updatedDepartement = new Departement();
        updatedDepartement.setIdDepartement(1);
        updatedDepartement.setNomDepart("Nouveau Nom");

        // Configurer le comportement du repository pour simuler la récupération du département existant
        when(departementRepository.findById(1)).thenReturn(Optional.of(existingDepartement));

        // Configurer le comportement du repository pour simuler la sauvegarde du département mis à jour
        when(departementRepository.save(any(Departement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Appeler la méthode de mise à jour du service
         departementService.updateDepartement(updatedDepartement);

        // Assurer que le résultat est le département mis à jour
        Assertions.assertEquals(existingDepartement, updatedDepartement);

        // Vérifier que le repository a été appelé avec le bon département mis à jour
        verify(departementRepository).save(updatedDepartement);

    }




}
