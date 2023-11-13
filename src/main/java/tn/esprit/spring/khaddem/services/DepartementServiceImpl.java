package tn.esprit.spring.khaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements IDepartementService{
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        departementRepository.save(d);
        return d;
    }

    @Override
    public Departement updateDepartement(Departement updatedDepartement) {
        Optional<Departement> existingDepartementOptional = departementRepository.findById(updatedDepartement.getIdDepartement());

        if (existingDepartementOptional.isPresent()) {
            Departement existingDepartement = existingDepartementOptional.get();

            // Mettez à jour les propriétés du département existant avec les nouvelles valeurs
            existingDepartement.setNomDepart(updatedDepartement.getNomDepart());

            // Enregistrez les modifications dans le dépôt
            departementRepository.save(existingDepartement);

            return existingDepartement; // Retournez le département mis à jour
        } else {
            // Gérez le cas où le département n'est pas trouvé
            throw new NotFoundException("Le département avec l'ID " + updatedDepartement.getIdDepartement() + " n'a pas été trouvé.");
        }
    }


    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);

        if (optionalDepartement.isPresent()) {
            return optionalDepartement.get();
        } else {
            // Gérez le cas où le département n'est pas trouvé
            throw new NotFoundException("Le département avec l'ID " + idDepart + " n'a pas été trouvé.");
        }
    }


    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        if (optionalUniversite.isPresent()) {
            Universite universite = optionalUniversite.get();
            return universite.getDepartements();
        } else {
            // Gérer le cas où l'université n'est pas présente
            return Collections.emptyList(); // Ou une autre valeur par défaut
        }
    }
}
