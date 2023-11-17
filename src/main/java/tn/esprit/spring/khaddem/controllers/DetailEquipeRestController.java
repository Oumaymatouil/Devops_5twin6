package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.DetailEquipeDTO;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.services.*;

import java.util.List;

@Slf4j
@RestController
@Controller
@RequestMapping("/details-equipe")
@AllArgsConstructor
public class DetailEquipeRestController {

    DetailEquipeServiceImpl detailEquipeService;



    // http://localhost:8089/Kaddem/equipe/retrieve-all-detail-equipes
    @GetMapping("/retrieve-all-detail-equipe")
    @ResponseBody
    public List<DetailEquipe> getDetailEquipes() {
        log.warn("hello world");
        return detailEquipeService.retrieveAllDetailEquipe();
    }


    // http://localhost:8089/Kaddem/equipe/detail-retrieve-equipe/8
    @GetMapping("/retrieve-detail-equipe/{detail-equipe-id}")
    @ResponseBody
    public DetailEquipe retrieveDetailEquipe(@PathVariable("detail-equipe-id") Integer detailEquipeId) {
        return detailEquipeService.retrieveDetailEquipe(detailEquipeId);
    }


    // http://localhost:8089/Kaddem/equipe/add-equipe

    @PostMapping("/add-detail-equipe")
    @ResponseBody
    public DetailEquipe addEquipe(@RequestBody DetailEquipeDTO detailEquipeDTO) {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setIdDetailEquipe(detailEquipeDTO.getIdDetailEquipe());
        detailEquipe.setSalle(detailEquipeDTO.getSalle());
        detailEquipe.setThematique(detailEquipeDTO.getThematique());
        return detailEquipeService.addDetailEquipe(detailEquipe);
    }

    // http://localhost:8089/Kaddem/equipe/update-detail-equipe
    @PutMapping("/update-detail-equipe")
    @ResponseBody
    public DetailEquipe updateDetailEquipe(@RequestBody DetailEquipeDTO detailEquipeDTO) {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setIdDetailEquipe(detailEquipeDTO.getIdDetailEquipe());
        detailEquipe.setSalle(detailEquipeDTO.getSalle());
        detailEquipe.setThematique(detailEquipeDTO.getThematique());
        return detailEquipeService.updateDetailEquipe(detailEquipe);
    }

    // http://localhost:8089/Kaddem/equipe/detail-retrieve-by-equipe/8
    @GetMapping("/retrieve-detail-by-equipe/{detail-equipe-id}")
    @ResponseBody
    public DetailEquipe retrieveDetailEquipeByEquipeId(@PathVariable("detail-equipe-id") Integer equipeId) {
        return detailEquipeService.retrieveDetailEquipeByEquipeId(equipeId);
    }

    // http://localhost:8089/Kaddem/equipe/detail-retrieve-by-equipe/8
    @PostMapping("/add-detail-to-equipe/{detail-equipe-id}/{equipe-id}")
    @ResponseBody
    public DetailEquipe addDetailToEquipe(@PathVariable("detail-equipe-id") Integer detailEquipeId, @PathVariable("equipe-id") Integer equipeId) {
        return detailEquipeService.addEquipeToDetailEquipe(equipeId, detailEquipeId);
    }


}
