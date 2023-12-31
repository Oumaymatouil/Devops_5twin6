package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContratRepositoryTest {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;


    @Test
    void getnbContratsValidesTest() {
        // Create and persist Contrat entities in the in-memory database
        Date startDate = new Date(); // Set start date
        Date endDate = new Date(); // Set end date

        // Create and persist Contrat entities in the in-memory database
        Contrat contrat1 = new Contrat();
        contrat1.setDateDebutContrat(startDate);
        contrat1.setDateFinContrat(endDate);
        contrat1.setArchived(true);

        contratRepository.save(contrat1);

        Contrat contrat2 = new Contrat();
        // Set appropriate dates for contrat2
        // contrat2.setDateDebutContrat(startDate);
        // contrat2.setDateFinContrat(endDate);
        // contrat2.setArchived(true);
        contratRepository.save(contrat2);

        Integer nbContrats = contratRepository.getnbContratsValides(startDate, endDate);

        assertEquals(1, nbContrats);
    }

    @Test
    void findByEtudiantIdEtudiantTest(){
        Etudiant etudiant = new Etudiant();
        etudiantRepository.save(etudiant);

        Contrat contrat1 = new Contrat();
        contrat1.setEtudiant(etudiant);
        contratRepository.save(contrat1);

        Contrat contrat2 = new Contrat();
        contrat2.setEtudiant(etudiant);
        contratRepository.save(contrat2);

        List<Contrat> contratsForEtudiant = contratRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant());

        assertEquals(2, contratsForEtudiant.size());
    }
}
