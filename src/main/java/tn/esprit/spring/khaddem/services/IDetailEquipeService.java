package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.DetailEquipe;

import java.util.List;

public interface IDetailEquipeService {
    List<DetailEquipe> retrieveAllDetailEquipe();

    DetailEquipe addDetailEquipe(DetailEquipe d);

    DetailEquipe updateDetailEquipe(DetailEquipe d);

    DetailEquipe retrieveDetailEquipe(Integer idDetailEquipe);



}
