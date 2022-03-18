package tn.esprit.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.auth.entity.Commande;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{
	public Commande findByReference(String ref);
	
	public List<Commande> findAllByReference(Long id);
	public Boolean existsByReference(Long ref);
	
	public Commande findByUser_Id(Long id);
	
	@Query(value = "SELECT codepromo, COUNT(codepromo) AS `value_occurrence` FROM Commande GROUP BY codepromo ORDER BY `value_occurrence` DESC LIMIT 1;", nativeQuery = true)
	public String mostCode();
	
	@Query(value = "SELECT EXTRACT(MONTH FROM date), COUNT(EXTRACT(MONTH FROM date)) AS `value_occurrence` FROM Commande GROUP BY EXTRACT(MONTH FROM date) ORDER BY `value_occurrence` DESC LIMIT 1", nativeQuery = true)
	public String mostDate();
	

}
