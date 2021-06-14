package tn.esprit.auth.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.auth.user.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	
	Optional<User> findByUserDetails_Username(String username);	
	
}
