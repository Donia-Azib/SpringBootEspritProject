package tn.esprit.auth.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.auth.user.model.User;
import tn.esprit.auth.user.model.UserDetailsImpl;
import tn.esprit.demande.service.DemandeRequest;

public interface UserRepository extends JpaRepository<User, Long>{
	
	
	Optional<User> findByUserDetails_Username(String username);

	UserDetailsImpl save(UserDetailsImpl user);

	
	
}
