package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    UniversiteRepository universiteRepository;
    DepartementRepository departementRepository;

    private static final String NOTFOUND ="introuvable universite";
    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        log.debug("u :" + u.getNomUniv());
        universiteRepository.save(u);
        return u;
    }

    @Override
    public Universite updateUniversite(Universite u) {
        Optional<Universite> universite = universiteRepository.findById(u.getIdUniversite());
        if (universite.isPresent()) {
            return u;
        }
        throw new NotFoundException(NOTFOUND);
    }

    @Override
    public Universite retrieveUniversite(Integer idUniversite) {
        Optional<Universite> universite = universiteRepository.findById(idUniversite);
        if (universite.isPresent()) {
            return universite.get();
        }
        throw new NotFoundException(NOTFOUND);

    }

    @Override
    public void removeUniversite(Integer idUniversite) {
        Optional<Universite> universite = universiteRepository.findById(idUniversite);
        if (universite.isPresent()) {
            universiteRepository.deleteById(idUniversite);
        } else {
            throw new NotFoundException(NOTFOUND);
        }
    }

    @Transactional
    public void assignUniversiteToDepartement(Integer universiteId, Integer departementId) {

        Optional<Universite> universite = universiteRepository.findById(universiteId);
        Optional<Departement> departement = departementRepository.findById(departementId);
        if (universite.isPresent() && (departement.isPresent())) {
            universite.get().getDepartements().add(departement.get());
        } else {
            throw new NotFoundException(NOTFOUND);
        }
    }
}
