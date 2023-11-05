package tn.esprit.spring.khaddem.controllers;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Specialite;

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
//    @ManyToOne
    // @JsonIgnore
//    private  Etudiant etudiant;



}
