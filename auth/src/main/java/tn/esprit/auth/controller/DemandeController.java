package tn.esprit.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.auth.entity.Demande;
import tn.esprit.auth.entity.Offre;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.service.DemandeService;

import java.util.List;

@RestController
@RequestMapping("/demande")
public class DemandeController {

    //	CRUD :
//	- add ( demande )
//	- update [ demande + info ] **
//	- delete [deleteById* / deleteAll* ] **
//	- READ [all* / byId*] **



    @Autowired
    private final DemandeService service;


    public DemandeController(DemandeService demandeSer) {
        this.service = demandeSer;
    }


    //	------------------READ
    @GetMapping("")
    public List<Demande> findAll(){
        return service.findAll();
    }

    @GetMapping("/{demandeId}")
    public Response<Demande> findById(@PathVariable Long demandeId)
    {
        return service.findById(demandeId);
    }


    //	------------------ADD
    @PostMapping("")
    public Demande save(@RequestBody Demande demande)
    {
        return service.save(demande);
    }




    //	------------------UPDATE
    @PutMapping("/{demandeId}")
    public Response<Demande> updateDemandeById(@PathVariable Long demandeId, @RequestBody Demande demande)
    {
        return service.update(demande, demandeId);

    }


    //	------------------DELETE
    @DeleteMapping("/{demandeId}")
    public Response<Boolean> deleteById(@PathVariable Long demandeId)
    {
        return service.deleteById(demandeId);
    }










}
