package tn.esprit.auth.service;

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
import tn.esprit.auth.repository.CommandeRepository;
import tn.esprit.auth.repository.PanierRepository;

@Service
@Transactional
public class PanierService {
	
	@Autowired
	private PanierRepository panierRepo;

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


	
}
