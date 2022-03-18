package tn.esprit.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.service.LivreService;


@RestController
@RequestMapping("/livre")
public class LivreController {
	
	private final LivreService livreSer;
	
	@Autowired
	public LivreController(LivreService livreSer) {
		this.livreSer = livreSer;
	}
	
	@PostMapping("/add")
	public Livre addBook(@RequestBody Livre livre)
	{
		return livreSer.save(livre);
	}
	
	@GetMapping("")
	public List<Livre> allBook()
	{
		return livreSer.findAll();
	}
	
	@GetMapping(value="/{ref}")
	public Response<Livre> findById(@PathVariable Long ref) {
		return livreSer.findById(ref);
	}
	
	@DeleteMapping(value="/{ref}")
	public Response<Boolean> deleteById(@PathVariable Long ref) {
		return	livreSer.deleteById(ref);
	} 
	
	@PutMapping(value="/{id}")
	public Response<Livre> Edit(@RequestBody Livre livre,@PathVariable Long id) {	
		return livreSer.update(livre,id);
	}

}
