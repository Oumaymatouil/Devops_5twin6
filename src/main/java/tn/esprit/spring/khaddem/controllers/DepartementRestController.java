package tn.esprit.spring.khaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.DepartementDTO;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.services.IDepartementService;

import java.util.List;

@RestController
@RequestMapping("/departement")

public class DepartementRestController {
    @Autowired
    IDepartementService departementService;
    // http://localhost:8089/Kaddem/departement/retrieve-all-departements
    @GetMapping("/retrieve-all-departements")
    @ResponseBody
    public List<Departement> getDepartements() {
        return departementService.retrieveAllDepartements();

    }

    // http://localhost:8089/Kaddem/departement/retrieve-departement/8
    @GetMapping("/retrieve-departement/{departement-id}")
    @ResponseBody
    public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }

    // http://localhost:8089/Kaddem/departement/add-departement
    @PostMapping("/add-departement")
    @ResponseBody
    public Departement addDepartement(@RequestBody DepartementDTO d) {
        Departement departement= new Departement();
        departement.setNomDepart(d.getNomDepart());
        departement.setEtudiants(d.getEtudiants());
        return  departementService.addDepartement(departement);


    }

    // http://localhost:8089/Kaddem/departement/update-departement
    @PutMapping("/update-departement")
    @ResponseBody
    public Departement updateDepartement(@RequestBody DepartementDTO d) {
        Departement departement= new Departement();
        departement.setNomDepart(d.getNomDepart());
        departement.setEtudiants(d.getEtudiants());
        return  departementService.updateDepartement(departement);

    }



    // http://localhost:8089/Kaddem/departement/retrieveDepartementsByUniversite/1
    @GetMapping("/retrieveDepartementsByUniversite/{idUniversite}")
    @ResponseBody
    public List<Departement> retrieveDepartementsByUniversite(@PathVariable("idUniversite") Integer idUniversite) {
        return  departementService.retrieveDepartementsByUniversite(idUniversite);

    }


}
