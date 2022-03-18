package tn.esprit.auth.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.auth.entity.Commande;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.model.ResponseService;
import tn.esprit.auth.repository.CommandeRepository;
import tn.esprit.auth.repository.LivreRepository;
import tn.esprit.auth.user.model.User;

@Service
@Transactional
public class CommandeService {
	
	@Autowired
	private CommandeRepository commandeRepo;
	
	@Autowired
	private LivreRepository livreRepo;

	public <S extends Commande> S save(S commande) {
		return commandeRepo.save(commande);
	}

	public Response<Commande> update(Commande commande, Long id) {
		Boolean exist = existsById(id);
		
		if (exist) {
			User user = commandeRepo.findById(id).get().getUser() ;
			commande.setId(id);
			commande.setLivres(livreRepo.findAllByUsers_Id(user.getId()));
			commande.setUser(user);
			Commande inBDD = commandeRepo.findById(id).get() ;
			commande.setDate(inBDD.getDate());
			commande.setReference(inBDD.getReference());
			commande.setTotal(inBDD.getTotal());
			commande.setCodepromo(inBDD.getCodepromo());
			
			commandeRepo.save(commande);
			return new ResponseService<Commande>().getSuccessResponse(commande);
		} else
			return new ResponseService<Commande>().getFailedResponse("This commande does not exist ...");

	}

	public Page<Commande> findAll(Pageable pageable) {
		return commandeRepo.findAll(pageable);
	}

	public List<Commande> findAll() {
		return commandeRepo.findAll();
	}
	
	public String findMostCommande() {
		return commandeRepo.mostCode(); 
	}
	
	public String findMostCommandeDate(){
		return commandeRepo.mostDate();
	}
	
	public Response<Commande> findByRef(Long ref) {
		if (existsById(ref))
			return new ResponseService<Commande>().getSuccessResponse(commandeRepo.findById(ref).get());
		else
			return new ResponseService<Commande>().getFailedResponse("This commande does not exist ...");
	}

	public boolean existsById(Long id) {
		return commandeRepo.existsById(id);
	}

	public long count() {
		return commandeRepo.count();
	}

	public Response<Boolean> deleteById(Long id) {
		if (existsById(id)) {
			commandeRepo.deleteById(id);
			return new ResponseService<Boolean>().getSuccessResponse(true);
		} else
			return new ResponseService<Boolean>().getFailedResponse("this commande does not exist ...");
	}
}
