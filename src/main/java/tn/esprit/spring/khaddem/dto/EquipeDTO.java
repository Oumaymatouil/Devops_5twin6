package tn.esprit.spring.khaddem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class EquipeDTO {

    private Integer idEquipe;

    private String nomEquipe;
    private Niveau niveau;

    @ManyToMany(mappedBy = "equipes",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Etudiant> etudiants;
    @OneToOne(cascade = CascadeType.ALL)
    private DetailEquipe detailEquipe;
}
