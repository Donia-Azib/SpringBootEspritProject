package tn.esprit.auth.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import tn.esprit.auth.entity.Reclamation;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long>{
	
	public Reclamation findByCommandeReference(Long id);
	public List<Reclamation> findAllByCommandeReference(Long id);
	public Boolean existsByCommandeReference(Long id);
	 
}
