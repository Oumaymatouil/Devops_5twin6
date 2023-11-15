package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EtudiantServiceImplTest {
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Mock
    EtudiantRepository etudiantRepository;
    @InjectMocks
    EtudiantServiceImpl etudiantService;

  Etudiant e = new Etudiant(1,"sofien","Bensalem", Option.GAMIX,new Departement(),new ArrayList<Equipe>(),new ArrayList<Contrat>());

    @Test
    void retrieveAllEtudiants() {
        List<Etudiant> etudiantList = etudiantService.retrieveAllEtudiants();
        Assertions.assertNotNull(etudiantList);
    }

    @Test
    void addEtudiant() {
    }

    @Test
    void updateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(e);
        Etudiant result = etudiantService.updateEtudiant(e);
        verify(etudiantRepository, times(1)).save(e);
    }

    @Test
    void retrieveEtudiant() {
    }

    @Test
    void removeEtudiant() {
        etudiantService.removeEtudiant(1);
        Mockito.verify(etudiantRepository,times(1))
                .deleteById(1);
    }

    @Test
    void assignEtudiantToDepartement() {
    }

    @Test
    void findByDepartementIdDepartement() {
    }

    @Test
    void findByEquipesNiveau() {
    }

    @Test
    void retrieveEtudiantsByContratSpecialite() {
    }

    @Test
    void retrieveEtudiantsByContratSpecialiteSQL() {
    }

    @Test
    void addAndAssignEtudiantToEquipeAndContract() {
    }

    @Test
    void getEtudiantsByDepartement() {
    }
}