package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;

class EtudiantTest {

    @Test
    void testEtudiantToString() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setPrenomE("John");
        etudiant.setNomE("Doe");
        etudiant.setOp(Option.SAE);

        String expectedString = "Etudiant{" +
                "idEtudiant=1, " +
                "prenomE='John', " +
                "nomE='Doe', " +
                "op=SAE" +
                '}';
        Assertions.assertEquals(expectedString, etudiant.toString());
    }

    @Test
    void testEtudiantGettersAndSetters() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setPrenomE("John");
        etudiant.setNomE("Doe");
        etudiant.setOp(Option.SE);

        Assertions.assertEquals(1, etudiant.getIdEtudiant());
        Assertions.assertEquals("John", etudiant.getPrenomE());
        Assertions.assertEquals("Doe", etudiant.getNomE());
        Assertions.assertEquals(Option.SE, etudiant.getOp());

    }
}