package tn.esprit.auth.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import tn.esprit.auth.entity.Commande;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.Promo;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.repository.CommandeRepository;
import tn.esprit.auth.repository.OffreRepository;
import tn.esprit.auth.repository.PanierRepository;
import tn.esprit.auth.repository.PromoRepository;
import tn.esprit.auth.service.CommandeService;
import tn.esprit.auth.service.NotificationService;
import tn.esprit.auth.service.PanierService;
import tn.esprit.auth.user.model.User;
import tn.esprit.auth.user.repository.UserRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommandeController {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CommandeService commandeSer;
	
	@Autowired
	private CommandeRepository commandeRepo;
	
	@Autowired
	private PanierService PanierSer;
	
	@Autowired
	private PanierRepository panierRepo;
	
	@Autowired
	private OffreRepository offreRepo;
	
	@Autowired
	private PromoRepository promoRepo;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	public CommandeController(CommandeService commandeSer) {
		this.commandeSer = commandeSer;
	}
	
	@PostMapping("/user/{userId}/commande") // passer la commande
	public Commande addCommande(@PathVariable Long userId, @Valid @RequestBody Commande commande) throws NotFoundException
	{	
		List<Livre> list = new ArrayList<>();
		list.addAll(PanierSer.findPanierListLivre(userId));
		String ref = "CMD"+userId;
		LocalDate localDate = LocalDate.now();
		return userRepo.findById(userId).map(user ->{
			commande.setUser(user);
			commande.setLivres(list);
			commande.setReference(ref);
			commande.setDate(localDate.toString());
			commande.setStatus("en cours");
			
			float total = 0  ;
			
			if(promoRepo.existsByCodepromo(commande.getCodepromo()) ){
				
				Promo promo = promoRepo.findByCodepromo(commande.getCodepromo());
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateDebutValidité = promo.getDatedebut();
				String dateFinValidité = promo.getDatefin() ;
				LocalDate DateDebutValidité = LocalDate.parse(dateDebutValidité, dtf);
				LocalDate DateFinValidité = LocalDate.parse(dateFinValidité, dtf);
			
				String dateDuJour = localDate.toString() ; 
				LocalDate DateDuJour = LocalDate.parse(dateDuJour, dtf);
				
				if(DateFinValidité.isAfter(DateDuJour) && DateDebutValidité.isBefore(DateDuJour) )
				{
					total = panierRepo.findByUser_Id(userId).getTotal() * promo.getPourcentage();	
				}
				else{
					total = panierRepo.findByUser_Id(userId).getTotal();
				}
				
			}else{
				total = panierRepo.findByUser_Id(userId).getTotal();
			}
			commande.setTotal(total);
			
			//send email
			try{
				notificationService.sendNotification(userRepo.getById(userId),"votre commande a bien été passé");
			}catch(MailException e){
				System.out.println("errrooooorrr");
			}
			
			return commandeSer.save(commande);
			}).orElseThrow(() -> new NotFoundException("User introuvable"));
	
	}
	
	@GetMapping("/commande/all")
	public List<Commande> allCommandes()
	{
		return commandeSer.findAll();
	}
	
	@GetMapping(value="/commande/find/{ref}")
	public Response<Commande> findByRef(@PathVariable Long ref) {
		return commandeSer.findByRef(ref);
	}
	
	@GetMapping(value="/commande/codePromo")  //le code promo le plus utilisé 
	public String stat() {
		String result = commandeSer.findMostCommande();
		return "le code promo le plus utilisé est " +  result.replace(",", " utilisé au total ") + " fois";
	}
	
	@GetMapping(value="/commande/stat/date")  //la periode ou on a eu le plus de vente
	public String statDate() {
		String mois = "";
		String result = commandeSer.findMostCommandeDate();
		int month = Integer.parseInt(result.split(",")[0]);
		switch(month){
		case 1 : mois = "Janvier"; break;
		case 2 : mois = "Fevier"; break;
		case 3 : mois = "Mars"; break;
		case 4 : mois = "Avril"; break;
		case 5 : mois = "Mai"; break;
		case 6 : mois = "Juin"; break;
		case 7 : mois = "Juillet"; break;
		case 8 : mois = "Aout"; break;
		case 9 : mois = "Septembre"; break;
		case 10 : mois = "Octobre"; break;
		case 11 : mois = "Novembre"; break;
		case 12 : mois = "Decembre"; break;
		}
		return "On a eu un pique de vente pendant le mois de " + mois+ "\n"+ "total de commandes passés au cours de ce mois: " + result.split(",")[1]+ " commandes";
	}
	
	@DeleteMapping(value="/commande/{ref}")
	public Response<Boolean> deleteById(@PathVariable Long ref) {
		return	commandeSer.deleteById(ref);
	} 
	
	@PutMapping(value="/commande/{id}")
	public Response<Commande> Edit(@RequestBody Commande commande,@PathVariable Long id) {
		User user = commandeRepo.findById(id).get().getUser() ;
		String status = commande.getStatus() ;
		if(status.equals("Terminer") ){
			
			try{
				String Text = "Votre commande est sur le chemin";
				notificationService.sendNotification(user,Text);
				
			}catch(MailException e){
				System.out.println("errrooooorrr");
			}
		}
		else if(status.equals("En cours de Traitement") ){
			
			try{
				String Text = "Votre réclamation concernant cette commande est en cours de traitement";
				notificationService.sendNotification(user,Text);
			}catch(MailException e){
				System.out.println("errrooooorrr");
			}
		}
		
		return commandeSer.update(commande,id);
	}

}
