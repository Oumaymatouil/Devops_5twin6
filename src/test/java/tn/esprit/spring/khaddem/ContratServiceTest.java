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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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


}
