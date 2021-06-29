package tn.esprit.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.auth.entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long>{
	public Livre findByOffreReference(Long id);
	public List<Livre> findAllByOffreReference(Long id);
	public Boolean existsByOffreReference(Long id);
	public List<Livre> findAllByUsers_Id(Long id);
	
}
