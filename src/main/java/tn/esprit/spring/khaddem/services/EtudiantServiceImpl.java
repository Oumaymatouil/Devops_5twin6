package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {
    EtudiantRepository etudiantRepository;
    DepartementRepository departementRepository;
    ContratRepository contratRepository;
    EquipeRepository equipeRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        if (e.getIdEtudiant() != null) {
            etudiantRepository.save(e);
            return e;
        } else {
            throw new IllegalArgumentException("Cannot update a non-persisted DetailEquipe.");
        }
    }

    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        Optional<Etudiant> optional = etudiantRepository.findById(idEtudiant);
        return optional.orElse(null);
    }

    @Override
    public void removeEtudiant(Integer idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        Optional<Etudiant> e = etudiantRepository.findById(etudiantId);
        if (!e.isPresent()) {
            return;
        }
        Optional<Departement> d = departementRepository.findById(departementId);
        if (!d.isPresent()) {
            return;
        }
        Etudiant etudiant = e.get();
        etudiant.setDepartement(d.get());
        etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> findByDepartementIdDepartement(Integer idDepartement) {
        return etudiantRepository.findByDepartementIdDepartement(idDepartement);
    }

    @Override
    public List<Etudiant> findByEquipesNiveau(Niveau niveau) {
        return etudiantRepository.findByEquipesNiveau(niveau);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(Specialite specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(String specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {

        Optional<Contrat> c = contratRepository.findById(idContrat);
        if (!c.isPresent()) {
            return null;
        }
        Contrat contrat = c.get();


        Optional<Equipe> eq = equipeRepository.findById(idEquipe);
        if (!eq.isPresent()) {
            return null;
        }
        Equipe equipe = eq.get();

        Etudiant etudiant = etudiantRepository.save(e);
        log.info("contrat: " + contrat.getSpecialite());
        log.info("equipe: " + equipe.getNomEquipe());
        log.info("etudiant: " + etudiant.getNomE() + " " + etudiant.getPrenomE() + " " + etudiant.getOp());
        List<Equipe> equipesMisesAjour = new ArrayList<>();
        contrat.setEtudiant(etudiant);
        if (etudiant.getEquipes() != null) {
            equipesMisesAjour = etudiant.getEquipes();
        }
        equipesMisesAjour.add(equipe);
        log.info("taille apres ajout : " + equipesMisesAjour.size());
        etudiant.setEquipes(equipesMisesAjour);


        return e;
    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        Optional<Departement> d = departementRepository.findById(idDepartement);
        if (!d.isPresent()) {
            return new ArrayList<>();
        }
        Departement departement = d.get();
        return departement.getEtudiants();

    }


}
