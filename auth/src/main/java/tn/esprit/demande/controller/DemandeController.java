package tn.esprit.demande.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.auth.user.model.User;
import tn.esprit.auth.user.model.UserDetailsImpl;
import tn.esprit.auth.user.repository.UserRepository;
import tn.esprit.demande.repository.DemandeRepository;
import tn.esprit.demande.service.DemandeRequest;

@RestController
@RequestMapping("/demande")
public class DemandeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DemandeRepository demandeRepository;
	
	
	
	@PostMapping("/placeDemande")
	public UserDetailsImpl placeDemande (@RequestBody DemandeRequest request ){
		
    	return	 userRepository.save(request.getUser());
		 
	}
	
	
	@GetMapping("/findAllDemandes")
	public List<User> findAllDemandes(){
		
		return userRepository.findAll();
				
	}
	
	
	
	

}
