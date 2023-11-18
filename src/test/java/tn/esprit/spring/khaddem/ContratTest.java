package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.khaddem.controllers.ContratDTO;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

 class ContratTest {

    @Test
     void testContrat() {
        // Create a Contrat object for testing
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setSpecialite(Specialite.IA);
        contrat.setArchived(true);
        contrat.setMontantContrat(1000);
        Etudiant etudiant = new Etudiant(); // Assuming Etudiant class is available
        contrat.setEtudiant(etudiant);

        // Test the getters
        assertEquals(1, contrat.getIdContrat());
        assertNotNull(contrat.getDateDebutContrat());
        assertNotNull(contrat.getDateFinContrat());
        assertEquals(Specialite.IA, contrat.getSpecialite());
        assertTrue(contrat.getArchived());
        assertEquals(1000, contrat.getMontantContrat());
        assertEquals(etudiant, contrat.getEtudiant());

        // Test the toString method
        assertNotNull(contrat.toString());

        // Test the constructors
        Contrat contratWithId = new Contrat(2, new Date(), new Date(), Specialite.IA, false, 1500, etudiant);
        assertEquals(2, contratWithId.getIdContrat());
        assertEquals(Specialite.IA, contratWithId.getSpecialite());

        ContratDTO contratDTO = new ContratDTO();
        Contrat contratFromDTO = new Contrat(contratDTO);
        assertNotNull(contratFromDTO);
    }
}