package tn.esprit.auth.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.Panier;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.repository.LivreRepository;
import tn.esprit.auth.repository.PanierRepository;
import tn.esprit.auth.service.LivreService;
import tn.esprit.auth.service.PanierService;
import tn.esprit.auth.user.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/panier")
public class PanierController {

	@Autowired
	private LivreService LivreSer;
	
	@Autowired
	private PanierService PanierSer;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PanierRepository panierRepo;
	
	@Autowired
	private LivreRepository livreRepo;
	
	
	@Autowired
	public PanierController(PanierService PanierSer) {
		this.PanierSer = PanierSer;
	}
	
	@PostMapping("/user/{userId}/panier")
	public Panier addPanier(@PathVariable Long userId, @RequestBody Panier panier) throws NotFoundException
	{
		float TTC = 0;
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Livre> lists = new ArrayList<>();
		lists.addAll(panier.getLivres());
	
		for (Livre l : lists) {
			String dateDeValidité = l.getOffre().getDate_fin() ;
			LocalDate DateDeValidité = LocalDate.parse(dateDeValidité, dtf);
			String dateDuJour = localDate.toString() ; 
			LocalDate DateDuJour = LocalDate.parse(dateDuJour, dtf);
			if(livreRepo.existsByOffreReference(l.getOffre().getReference()) && DateDeValidité.isAfter(DateDuJour) )
				{
					float pourcentage = l.getOffre().getPourcentage() ; 
					TTC = ((l.getPrix()*pourcentage)  * l.getQuantite()) + TTC ;

				}
			else {
				TTC = (l.getPrix() * l.getQuantite()) + TTC ;
			}
		}
		panier.setTotal(TTC);
		return userRepo.findById(userId).map(user ->{
			panier.setUser(user);
			return PanierSer.save(panier);
			}).orElseThrow(() -> new NotFoundException("User introuvable"));
	
	}
	
	@GetMapping("/all")
	public List<Panier> allPanier()
	{
		return PanierSer.findAll();
	}
	
	@GetMapping(value="/find/{userId}")
	public Panier findByUser_Id(@PathVariable Long userId) {
		return panierRepo.findByUser_Id(userId);
	}
	
	@GetMapping(value="/get/{userId}")
	public List<Livre> findLivrezz(@PathVariable Long userId) {
		return panierRepo.findByUser_Id(userId).getLivres();
	}
	
	@PutMapping(value="/{id}/{userId}")
	public Response<Panier> Edit(@RequestBody Panier panier,@PathVariable Long id, @PathVariable Long userId) {
	
	
		return PanierSer.update(panier,id,userId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
