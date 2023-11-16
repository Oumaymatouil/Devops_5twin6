package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.khaddem.KhaddemApplication;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EtudiantServiceImplTest {

    @Mock
    @Autowired
    private EquipeRepository equipeRepository;
    @Mock
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    @Mock
    EtudiantRepository etudiantRepository;
    @Autowired
    @Mock
    DepartementRepository departementRepository;
    @InjectMocks
    EtudiantServiceImpl etudiantService;
    @InjectMocks
    DepartementServiceImpl departementService;
    @InjectMocks
    ContratServiceImpl contratService;
    @InjectMocks
    EquipeServiceImpl equipeService;

  Etudiant e = new Etudiant(1,"sofien","Bensalem", Option.GAMIX,new Departement(),new ArrayList<Equipe>(),new ArrayList<Contrat>());


    @Test
    void retrieveAllEtudiants() {
        List<Etudiant> etudiantList = etudiantService.retrieveAllEtudiants();
        Assertions.assertNotNull(etudiantList);
    }



    @Test
    void updateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(e);
        Etudiant result = etudiantService.updateEtudiant(e);
        verify(etudiantRepository, times(1)).save(e);
    }



    @Test
    void removeEtudiant() {
        etudiantService.removeEtudiant(1);
        Mockito.verify(etudiantRepository,times(1))
                .deleteById(1);
    }

    @Test
    public void testAssignEtudiantToDepartement() {
        List<Etudiant> list= new ArrayList<Etudiant>() {
            {
                add(new Etudiant(1,"sofien","Bensalem", Option.GAMIX,new Departement(),new ArrayList<Equipe>(),new ArrayList<Contrat>()));
                add(new Etudiant(2,"soff","Bensalem", Option.GAMIX,new Departement(),new ArrayList<Equipe>(),new ArrayList<Contrat>()));
            }
        };
        // Mock the behavior of the repository
        Departement departement = new Departement(1,"TWIN",new ArrayList<Etudiant>());
        Etudiant etudiant = new Etudiant(1,"sofien","Bensalem", Option.GAMIX,new Departement(),new ArrayList<Equipe>(),new ArrayList<Contrat>());
        when(etudiantRepository.findById(1)).thenReturn(java.util.Optional.of(etudiant));
        when(departementRepository.findById(1)).thenReturn(java.util.Optional.of(departement));

        etudiantService.addEtudiant(etudiant);
        departementService.addDepartement(departement);
        etudiantRepository.save(etudiant);
        departementRepository.save(departement);

        // Invoke the service method
        etudiantService.assignEtudiantToDepartement(1, 1);
    }
    @Test
    public Etudiant testAddAndAssignEtudiantToEquipeAndContract() {
        List<Etudiant> list= new ArrayList<Etudiant>() {
            {
                add(new Etudiant(1,"sofien","Bensalem", Option.GAMIX,new Departement(),new ArrayList<Equipe>(),new ArrayList<Contrat>()));
                add(new Etudiant(2,"soff","Bensalem", Option.GAMIX,new Departement(),new ArrayList<Equipe>(),new ArrayList<Contrat>()));
            }
        };
        List<Equipe> equipes = new ArrayList<Equipe>(){
            {
            add(new Equipe(1,"team",Niveau.EXPERT,list,new DetailEquipe()));
            }
        };
        List<Contrat> contrats = new ArrayList<Contrat>(){
            {
                add(new Contrat(1,new Date(),new Date(),Specialite.IA,false,15,e));
            }
        };
        // Mock the behavior of the repository
        Etudiant e = new Etudiant(1,"sofien","Bensalem", Option.GAMIX,new Departement(),equipes,contrats);
        Contrat contrat = new Contrat(1,new Date(),new Date(),Specialite.IA,false,15,e);
        Equipe equipe = new Equipe(1,"team",Niveau.EXPERT,list,new DetailEquipe());
        when(contratRepository.findById(1)).thenReturn(java.util.Optional.of(contrat));
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.of(equipe));

        etudiantService.addEtudiant(e);
        etudiantRepository.save(e);
        contratService.addContrat(contrat);
        equipeService.addEquipe(equipe);
        contratRepository.save(contrat);
        equipeRepository.save(equipe);

        // Invoke the service method
        Etudiant result=etudiantService.addAndAssignEtudiantToEquipeAndContract(e, 1, 1);
        verify(contratRepository, times(1)).findById(1);
        verify(equipeRepository, times(1)).findById(1);
        verify(etudiantRepository, times(1)).save(result);
        return result;
    }

}

@SpringBootTest(classes = {KhaddemApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class EtudiantServiceImplJunitTest {

    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    EtudiantServiceImpl etudiantService;
    @Autowired
    DepartementServiceImpl departementService;
    @Autowired
    ContratServiceImpl contratService;
    @Autowired
    EquipeServiceImpl equipeService;

    Departement departement = new Departement(1,"TWIN",new ArrayList<Etudiant>());
    Equipe equipe = new Equipe(1,"team",Niveau.EXPERT,new ArrayList<Etudiant>(),new DetailEquipe());
    List<Equipe> equipes = new ArrayList<Equipe>(){
        {
            add(new Equipe(1,"team",Niveau.EXPERT,new ArrayList<Etudiant>(),new DetailEquipe()));
        }
    };
    List<Contrat> contrats = new ArrayList<Contrat>(){
        {
            add(new Contrat(1,new Date(),new Date(),Specialite.IA,false,15,e));
        }
    };
    Etudiant e = new Etudiant(1,"sofien","Bensalem", Option.GAMIX,departement,equipes,contrats);
    Contrat contrat = new Contrat(1,new Date(),new Date(),Specialite.IA,false,15,e);



    @Test
    @Order(1)
    void testAddEtudiant(){
        equipeService.addEquipe(equipe);
        contratService.addContrat(contrat);
        departementService.addDepartement(departement);
        etudiantService.addEtudiant(e);
        equipeRepository.save(equipe);
        contratRepository.save(contrat);
        departementRepository.save(departement);
        etudiantRepository.save(e);
        Etudiant etudiant= etudiantService.addEtudiant(e);
        Assertions.assertEquals("sofien",etudiant.getNomE());
    }

//    @Test
//    @Order(2)
//    void testRetrieveEtudiant(){
//        Etudiant etudiant = etudiantService.retrieveEtudiant(1);
//        Assertions.assertNotNull(etudiant);
//    }




}

