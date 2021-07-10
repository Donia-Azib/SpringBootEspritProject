package tn.esprit.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.auth.entity.Promo;



@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
	public Boolean existsByCodepromo(String codepromo);
	
	public Promo findByCodepromo(String codepromo);
}
