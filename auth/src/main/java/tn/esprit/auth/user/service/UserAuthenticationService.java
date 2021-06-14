package tn.esprit.auth.user.service;

import java.util.Optional;

import tn.esprit.auth.user.model.User;
import tn.esprit.auth.user.model.UserDetailsImpl;



public interface UserAuthenticationService {
	
	
	Optional<String> login(String username, String password);
	User findByToken(String token);
	void logout(UserDetailsImpl userDetails);
	
	
	
	
 
}
