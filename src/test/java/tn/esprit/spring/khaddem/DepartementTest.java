package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.khaddem.entities.Departement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class DepartementTest {

    @Test
    void testEquals() {
        Departement departement1 = Departement.builder()
                .idDepartement(1)
                .nomDepart("Computer Science")
                .etudiants(new ArrayList<>())
                .build();

        Departement departement2 = Departement.builder()
                .idDepartement(1)
                .nomDepart("Computer Science")
                .etudiants(new ArrayList<>())
                .build();

        assertEquals(departement1, departement2);
    }

    @Test
    void testNotEquals() {
        Departement departement1 = Departement.builder()
                .idDepartement(1)
                .nomDepart("Computer Science")
                .etudiants(new ArrayList<>())
                .build();

        Departement departement2 = Departement.builder()
                .idDepartement(2)
                .nomDepart("Electrical Engineering")
                .etudiants(new ArrayList<>())
                .build();

        assertNotEquals(departement1, departement2);
    }

    @Test
    void testHashCode() {
        Departement departement1 = Departement.builder()
                .idDepartement(1)
                .nomDepart("Computer Science")
                .etudiants(new ArrayList<>())
                .build();

        Departement departement2 = Departement.builder()
                .idDepartement(1)
                .nomDepart("Computer Science")
                .etudiants(new ArrayList<>())
                .build();

        assertEquals(departement1.hashCode(), departement2.hashCode());
    }

    @Test
    void testHashCodeNotEquals() {
        Departement departement1 = Departement.builder()
                .idDepartement(1)
                .nomDepart("Computer Science")
                .etudiants(new ArrayList<>())
                .build();

        Departement departement2 = Departement.builder()
                .idDepartement(2)
                .nomDepart("Electrical Engineering")
                .etudiants(new ArrayList<>())
                .build();

        assertNotEquals(departement1.hashCode(), departement2.hashCode());
    }
}
