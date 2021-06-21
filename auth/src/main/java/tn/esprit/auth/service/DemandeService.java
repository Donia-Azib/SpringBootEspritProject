package tn.esprit.auth.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.auth.entity.Demande;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.Offre;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.model.ResponseService;
import tn.esprit.auth.repository.DemandeRepository;
import tn.esprit.auth.repository.LivreRepository;
import tn.esprit.auth.repository.OffreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DemandeService {


    private static Logger logger = LogManager.getLogger(DemandeService.class);


    @Autowired
    private DemandeRepository demandeRepo;


    //	------------------READ




    public List<Demande> findAll() {
        List<Demande> demandes = new ArrayList<>();
        demandeRepo.findAll().forEach(demande -> {
            demandes.add(demande);
        });
        return demandes;
    }

    public Response<Demande> findById(Long id) {
                if (existsById(id)) {
            return new ResponseService<Demande>().getSuccessResponse(demandeRepo.findById(id).get());
        } else
            return new ResponseService<Demande>().getFailedResponse("This demand does not exist...");
    }

    public boolean existsById(Long id) {

        return (demandeRepo.existsById(id) && demandeRepo.findById(id).get().isCreated());
    }

    //	------------------ADD

    public <S extends Demande> S save(S demande) {
        return demandeRepo.save(demande);
    }

    //	------------------UPDATE
    public Response<Demande> update(Demande demande, Long did) {
        if (existsById(did)) {
             demande.setId(did);
            return new ResponseService<Demande>().getSuccessResponse(demandeRepo.save(demande));
        } else
            return new ResponseService<Demande>().getFailedResponse("this demand does not exist...");
    }

    //	------------------DELETE
    public Response<Boolean> deleteById(Long id) {
        if (existsById(id)) {
            demandeRepo.deleteById(id);
            return new ResponseService<Boolean>().getSuccessResponse(true);
        } else
            return new ResponseService<Boolean>().getFailedResponse("this demand does not exist...");
    }



}
