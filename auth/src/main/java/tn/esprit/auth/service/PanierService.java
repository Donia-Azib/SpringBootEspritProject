package tn.esprit.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.auth.entity.Commande;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.Panier;
import tn.esprit.auth.model.Response;
import tn.esprit.auth.model.ResponseService;
import tn.esprit.auth.repository.CommandeRepository;
import tn.esprit.auth.repository.PanierRepository;
import tn.esprit.auth.user.repository.UserRepository;

@Service
@Transactional
public class PanierService {
	
	@Autowired
	private PanierRepository panierRepo;
	
	@Autowired
	private UserRepository userRepo;

	public <S extends Panier> S save(S panier) {
		return panierRepo.save(panier);
	}
	
	public Page<Panier> findAll(Pageable pageable) {
		return panierRepo.findAll(pageable);
	}

	public List<Panier> findAll() {
		return panierRepo.findAll();
	}

	public List<Livre> findPanierListLivre(Long id) {
		return panierRepo.findByUser_Id(id).getLivres() ;
	}
	
	public Response<Panier> findById(Long id) {
		if (existsById(id))
			return new ResponseService<Panier>().getSuccessResponse(panierRepo.findById(id).get());
		else
			return new ResponseService<Panier>().getFailedResponse("This book does not exist ...");
	}

	public boolean existsById(Long id) {
		return panierRepo.existsById(id);
	}

	public Response<Panier> update(Panier panier, Long id, Long userId) {
		Boolean exist = existsById(id);
		if (exist) {
			List<Livre> list = new ArrayList<>();
			list.addAll(findPanierListLivre(userId));
			list.addAll(panier.getLivres());
			panier.setLivres(list);
			panier.setId(id);
			float ancienTotal = panierRepo.findById(id).get().getTotal() ; 
			float finalTotal = 0;
			 for(int i = 0 ; i <  panier.getLivres().size(); i++){
				 if(panier.getLivres().get(i).getOffre().getPourcentage() != 0){
					 finalTotal = ancienTotal + ((panier.getLivres().get(i).getPrix() * panier.getLivres().get(i).getQuantite()) * panier.getLivres().get(i).getOffre().getPourcentage() );
				 }
				 else{
					 finalTotal = ancienTotal + (panier.getLivres().get(i).getPrix() * panier.getLivres().get(i).getQuantite());
				 }
				
			 }
			panier.setTotal(finalTotal);
			panier.setUser(userRepo.findById(userId).get());
			panierRepo.save(panier);
			return new ResponseService<Panier>().getSuccessResponse(panier);
		} else
			return new ResponseService<Panier>().getFailedResponse("This book does not exist ...");
	}


	
}
