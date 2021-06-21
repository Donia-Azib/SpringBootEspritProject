package tn.esprit.auth.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.auth.entity.Demande;
import tn.esprit.auth.entity.Livre;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long> {



}
