package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipeServiceImplTest {
    @Mock
    EquipeRepository equipeRepository;
    @Mock
    DetailEquipeRepository detailEquipeRepository;
    @InjectMocks
    EquipeServiceImpl equipeServiceImpl;
    Equipe equipe1 = new Equipe(1, "Equipe1", Niveau.JUNIOR, new ArrayList<Etudiant>(), new DetailEquipe());
    List<Equipe> listEquipe = new ArrayList<Equipe>(){
        {
            add(new Equipe(1, "Equipe1", Niveau.JUNIOR, new ArrayList<Etudiant>(), new DetailEquipe()));
            add(new Equipe(2, "Equipe2", Niveau.JUNIOR, new ArrayList<Etudiant>(), new DetailEquipe()));
        }
    };
    @Test
    void retrieveAllEquipes() {
        System.out.println("In the function");
        // Mock the behavior of the repository
        when(equipeRepository.findAll()).thenReturn(listEquipe);

        // Call the method being tested
        System.out.println("Before calling retrieveAllEquipes()");
        List<Equipe> prods = equipeServiceImpl.retrieveAllEquipes();

        // Assert the result
        assertNotNull(prods);
    }

    @Test
    void addEquipe() {
        when(equipeRepository.save(equipe1)).thenReturn(equipe1);
        Equipe equipe = equipeServiceImpl.addEquipe(equipe1);
        assertNotNull(equipe);
    }

    @Test
    void updateEquipe() {
        Equipe equipe =new Equipe(1, "Equipe1", Niveau.JUNIOR, new ArrayList<Etudiant>(), new DetailEquipe());
        Mockito.when(equipeRepository.save(Mockito.any())).thenReturn(equipe);
        Equipe as=equipeServiceImpl.updateEquipe(equipe);
        assertEquals("Equipe1",as.getNomEquipe());
    }

    @Test
    void retrieveEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe1));

// Call the method being tested
        System.out.println("Before calling testretrieveEquipe()");
        Equipe pr = equipeServiceImpl.retrieveEquipe(1);
        System.out.println("After calling testretrieveEquipe() => " + pr.getNomEquipe());

// Assert the result
        assertNotNull(pr);
    }


}