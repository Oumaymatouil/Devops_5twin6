package tn.esprit.spring.khaddem.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;

import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
public class ContratDTO {
    private Integer idContrat;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;
    private Boolean archived;
    private Integer montantContrat;
    @ManyToOne
    @JsonIgnore
    private Etudiant etudiant;



}
