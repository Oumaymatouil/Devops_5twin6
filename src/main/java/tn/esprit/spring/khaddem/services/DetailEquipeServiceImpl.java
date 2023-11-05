package tn.esprit.spring.khaddem.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetailEquipeServiceImpl implements IDetailEquipeService {

    //@Autowired
    //EquipeRepository equipeRepository;

    private final DetailEquipeRepository detailEquipeRepository;

    public DetailEquipeServiceImpl(DetailEquipeRepository detailEquipeRepository) {
        this.detailEquipeRepository = detailEquipeRepository;
    }

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

    /* @Override
    public DetailEquipe retrieveDetailEquipeByEquipeId(Integer idEquipe) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);
        return equipeOptional.map(Equipe::getDetailEquipe).orElse(null);
    }*/

}
