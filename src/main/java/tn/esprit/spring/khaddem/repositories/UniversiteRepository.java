package tn.esprit.spring.khaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;



@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Integer> {
    public Universite findByNomUniv(String nomUniv);
    public Universite findByDepartements(Departement departement);


}
