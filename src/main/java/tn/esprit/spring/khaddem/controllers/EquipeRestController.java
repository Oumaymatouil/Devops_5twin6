package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.IEquipeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {


    IEquipeService equipeService;
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {
        return  equipeService.retrieveAllEquipes();
    }


    // http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
    @GetMapping("/retrieve-equipe/{equipe-id}")
    @ResponseBody
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }

    // http://localhost:8089/Kaddem/equipe/add-equipe
    /* cette méthode permet d'ajouter une équipe avec son détail*/
    @PostMapping("/add-equipe")
    @ResponseBody
    public Equipe addEquipe(@RequestBody EquipeDTO e) {
        Equipe equipe= new Equipe();
        equipe.setIdEquipe(e.getIdEquipe());
        equipe.setDetailEquipe(e.getDetailEquipe());
        equipe.setNomEquipe(e.getNomEquipe());
        equipe.setNiveau(e.getNiveau());
        equipe.setEtudiants(e.getEtudiants());
        return equipeService.addEquipe(equipe);

    }

    // http://localhost:8089/Kaddem/equipe/update-equipe
    @PutMapping("/update-equipe")
    @ResponseBody
    public Equipe updateEtudiant(@RequestBody EquipeDTO e) {
        Equipe equipe= new Equipe();
        equipe.setIdEquipe(e.getIdEquipe());
        equipe.setDetailEquipe(e.getDetailEquipe());
        equipe.setNomEquipe(e.getNomEquipe());
        equipe.setNiveau(e.getNiveau());
        equipe.setEtudiants(e.getEtudiants());
        return equipeService.updateEquipe(equipe);
    }

}
