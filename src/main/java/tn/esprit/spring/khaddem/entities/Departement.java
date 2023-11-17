package tn.esprit.spring.khaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartement;
    private String nomDepart;
    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Etudiant> etudiants;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Departement other = (Departement) obj;
        // Compare fields here
        return Objects.equals(idDepartement, other.idDepartement) &&
                Objects.equals(nomDepart, other.nomDepart) &&
                // Add other fields as needed
                Objects.equals(etudiants, other.etudiants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepartement, nomDepart, etudiants);
    }


}
