package tn.esprit.spring.khaddem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UniversiteTest {

    @Test
    void testUniversiteConstructor() {
        Universite universite = new Universite();
        assertNull(universite.getIdUniversite());
        assertNull(universite.getNomUniv());
        assertNull(universite.getDepartements());
    }

    @Test
    void testUniversiteBuilder() {
        List<Departement> departements = Arrays.asList(new Departement(), new Departement());
        Universite universite = Universite.builder()
                .idUniversite(1)
                .nomUniv("Test University")
                .departements(departements)
                .build();

        assertEquals(1, universite.getIdUniversite());
        assertEquals("Test University", universite.getNomUniv());
        assertEquals(departements, universite.getDepartements());
    }

    @Test
    void testUniversiteGettersAndSetters() {
        Universite universite = new Universite();

        universite.setIdUniversite(1);
        universite.setNomUniv("Test University");
        List<Departement> departements = Arrays.asList(new Departement(), new Departement());
        universite.setDepartements(departements);

        assertEquals(1, universite.getIdUniversite());
        assertEquals("Test University", universite.getNomUniv());
        assertEquals(departements, universite.getDepartements());
    }

    @Test
    void testUniversiteEqualsAndHashCode() {
        Universite universite1 = Universite.builder().idUniversite(1).build();
        Universite universite2 = Universite.builder().idUniversite(1).build();
        Universite universite3 = Universite.builder().idUniversite(2).build();

        assertNotEquals(universite1, universite2);
        assertNotEquals(universite1, universite3);

        assertNotEquals(universite1.hashCode(), universite2.hashCode());
        assertNotEquals(universite1.hashCode(), universite3.hashCode());
    }
}
