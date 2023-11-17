package tn.esprit.spring.khaddem.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Option;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class EtudiantDTO {

    private Integer idEtudiant;
    private String prenomE;
    private String nomE;
    private Option op;

    private Departement departement;
    @ManyToMany
    private List<Equipe> equipes;
    @OneToMany(mappedBy = "etudiant")
    private List<Contrat> contrats;

}
