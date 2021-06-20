package tn.esprit.demande.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.demande.model.Demande;

public interface DemandeRepository extends JpaRepository<Demande,Long>  {
	
	

}
