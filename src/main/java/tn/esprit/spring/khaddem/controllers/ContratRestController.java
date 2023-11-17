package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.services.IContratService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {
    IContratService contratService;

    @GetMapping("/retrieve-all-contrats")
    @ResponseBody
    public List<Contrat> getContrats() {
        return contratService.retrieveAllContrats();
    }

    @GetMapping("/retrieve-contrat/{contrat-id}")
    @ResponseBody
    public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
        return contratService.retrieveContrat(contratId);
    }

    @PostMapping("/add-contrat")
    @ResponseBody
    public Contrat addContrat(@RequestBody ContratDTO c) {
        Contrat contrat = new Contrat(c);
        return contratService.addContrat(contrat);
    }

    @PutMapping("/update-contrat")
    @ResponseBody
    public Contrat updateEtudiant(@RequestBody ContratDTO c) {
        Contrat contrat = new Contrat(c);
        return contratService.updateContrat(contrat);
    }

    @PostMapping("/addAndAffectContratToEtudiant/{nomE}/{prenomE}")
    @ResponseBody
    public Contrat addAndAffectContratToEtudiant(@RequestBody ContratDTO contrat,@PathVariable("nomE") String nomE,@PathVariable("prenomE") String prenomE) {
        Contrat con = new Contrat(contrat);
        return contratService.addAndAffectContratToEtudiant(con,nomE,prenomE);
    }

    //The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31".
    @GetMapping(value = "/getnbContratsValides/{startDate}/{endDate}")
    public Integer getnbContratsValides(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.nbContratsValides(startDate, endDate);
    }

    //Only no-arg methods may be annotated with @Scheduled
    @Scheduled(cron="0 0 13 * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    //  @Scheduled(cron="45 * * * * *")//(cron="0 0 13 * * ?")(fixedRate =21600)
    @PutMapping(value = "/majStatusContrat")
    public void majStatusContrat (){
        contratService.retrieveAndUpdateStatusContrat();
    }

    @GetMapping("/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    @ResponseBody
    public float calculChiffreAffaireEntreDeuxDates(@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                    @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }

}
