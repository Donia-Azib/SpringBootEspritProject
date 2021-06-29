package tn.esprit.auth.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.auth.entity.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long>{
	public Panier findByUser_Id(Long id);
}
