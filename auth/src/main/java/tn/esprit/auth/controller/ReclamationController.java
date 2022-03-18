package tn.esprit.auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import tn.esprit.auth.entity.Reclamation;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.repository.CommandeRepository;
import tn.esprit.auth.service.ReclamationService;
import tn.esprit.auth.user.repository.UserRepository;

@RestController
@RequestMapping("/rest")
public class ReclamationController {

	@Autowired
	private CommandeRepository commandeRepo;
	
	@Autowired
	private UserRepository userRepo;

	private final ReclamationService ReclamationSer;
	
	@Autowired
	public ReclamationController(ReclamationService ReclamationSer) {
		this.ReclamationSer = ReclamationSer;
	}
	
	@PostMapping("/commande/{commandeId}/{userId}/reclamation")
	public Reclamation addReclamation(@PathVariable Long commandeId,@PathVariable Long userId, @Valid @RequestBody Reclamation reclamation) throws NotFoundException
	{
		return userRepo.findById(userId).map(user ->{
			reclamation.setUser(user);
			reclamation.setCommande(commandeRepo.findById(commandeId).get());
			return ReclamationSer.save(reclamation);
			}).orElseThrow(() -> new NotFoundException("Commande introuvable"));
	
	}
	
	@GetMapping("/reclamation/all")
	public List<Reclamation> allReclamation()
	{
		return ReclamationSer.findAll();
	}
	
	@GetMapping(value="/find/{ref}")
	public Response<Reclamation> findById(@PathVariable Long ref) {
		return ReclamationSer.findById(ref);
	}
	
	@DeleteMapping(value="/{ref}")
	public Response<Boolean> deleteById(@PathVariable Long ref) {
		return	ReclamationSer.deleteById(ref);
	} 
	
	@PutMapping(value="/{id}")
	public Response<Reclamation> Edit(@RequestBody Reclamation reclamation,@PathVariable Long id) {	
		return ReclamationSer.update(reclamation,id);
	}

}
