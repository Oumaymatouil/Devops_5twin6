package tn.esprit.spring.khaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.esprit.spring.khaddem.dto.UniversiteDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Universite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniversite;
    private String nomUniv;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
   private List<Departement>departements;


    public Universite(UniversiteDTO universiteDTO){
        this.idUniversite=universiteDTO.getIdUniversite();
        this.nomUniv=universiteDTO.getNomUniv();
        this.departements=this.getDepartements();
    }


}
