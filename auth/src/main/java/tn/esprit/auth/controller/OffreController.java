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
import tn.esprit.auth.entity.Offre;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.service.OffreService;


@RestController
@RequestMapping("/offre")
public class OffreController {
//	CRUD : 
//	- add ( offre / offre + [livres] ) 
//	- update [ livre(ajout/supp) + info ] **
//	- delete [deleteById* / deleteAll* / deleteAllBooks* /deleteBook] **
//	- READ [all* / byId* / allBook* / bookById*] **
	
	@Autowired
	private OffreService service;
	
	
//	------------------READ
	@GetMapping("")
	public List<Offre> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{offreId}")
	public Response<Offre> findById(@PathVariable Long offreId)
	{
		return service.findById(offreId);
	}
	
	@GetMapping("/{offreId}/livres")
	public List<Livre> findAllBooks(@PathVariable Long offreId)
	{
		return service.findAllBooks(offreId);
	}
	
	@GetMapping("/{offreId}/livre/{livreId}")
	public Response<Livre> findBookById(@PathVariable Long offreId, @PathVariable Long livreId )
	{
		return service.findBookById(offreId);
	}
	
	
//	------------------ADD
	@PostMapping("")
	public Offre save(@RequestBody Offre offre)
	{
		return service.save(offre);
	}
	
	@PostMapping("/{offreId}/livre/{livreId}")
	public Response<Offre> saveBook(@PathVariable Long offreId ,@PathVariable Long livreId )
	{
		return service.saveBook(livreId , offreId);	
	}
	
	
//	------------------UPDATE 
	@PutMapping("/{offreId}")
	public Response<Offre> updateOffreById(@PathVariable Long offreId,@RequestBody Offre offre)
	{
		return service.update(offre, offreId);
	}
	
	
//	------------------DELETE
	@DeleteMapping("/{offreId}")
	public Response<Boolean> deleteById(@PathVariable Long offreId)
	{
		return service.deleteById(offreId);
	}
	
	@DeleteMapping("/{offreId}/livres")
	public Response<Boolean> deleteAllBooks(@PathVariable Long offreId)
	{
		return service.deleteBooks(offreId);
	}
	
	@DeleteMapping("/{offreId}/livre/{bookId}")
	public Response<Boolean> deleteBookById(@PathVariable Long offreId,@PathVariable Long bookId)
	{
		return service.deleteBookById(offreId,bookId);
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		service.deleteAll();
	}
	

}

