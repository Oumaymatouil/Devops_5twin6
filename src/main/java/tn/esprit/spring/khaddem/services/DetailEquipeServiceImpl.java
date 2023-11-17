package tn.esprit.spring.khaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetailEquipeServiceImpl implements IDetailEquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private DetailEquipeRepository detailEquipeRepository;

    @Override
    public List<DetailEquipe> retrieveAllDetailEquipe() {
        return this.detailEquipeRepository.findAll();
    }

    @Override
    public DetailEquipe addDetailEquipe(DetailEquipe d) {
        detailEquipeRepository.save(d);
        return d;
    }

    @Override
    public DetailEquipe updateDetailEquipe(DetailEquipe d) {
        if (d.getIdDetailEquipe() != null) {
            detailEquipeRepository.save(d);
            return d;
        } else {
            throw new IllegalArgumentException("Cannot update a non-persisted DetailEquipe.");
        }
    }

    @Override
    public DetailEquipe retrieveDetailEquipe(Integer idDetailEquipe) {
        Optional<DetailEquipe> detailEquipeOptional = detailEquipeRepository.findById(idDetailEquipe);
        return detailEquipeOptional.orElse(null);
    }

    @Override
    public DetailEquipe retrieveDetailEquipeByEquipeId(Integer idEquipe) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);
        return equipeOptional.map(Equipe::getDetailEquipe).orElse(null);
    }

    @Override
    public DetailEquipe addEquipeToDetailEquipe(int idEquipe, int idDetail) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);
        Optional<DetailEquipe> detailEquipeOptional = detailEquipeRepository.findById(idDetail);
        if (!detailEquipeOptional.isPresent() || !equipeOptional.isPresent()) {
            return null;
        }
        DetailEquipe detailEquipe = detailEquipeOptional.get();
        Equipe equipe = equipeOptional.get();
        equipe.setDetailEquipe(detailEquipe);
        equipeRepository.save(equipe);
        detailEquipe.setEquipe(equipe);
        detailEquipeRepository.save(detailEquipe);
        return detailEquipe;
    }

}
