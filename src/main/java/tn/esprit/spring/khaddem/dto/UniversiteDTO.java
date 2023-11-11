package tn.esprit.spring.khaddem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Departement;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class UniversiteDTO {
    private Integer idUniversite;
    private String nomUniv;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Departement> departements;

}
