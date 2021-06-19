package tn.esprit.auth.user.service;

import java.util.Map;
import java.util.Optional;

import org.hibernate.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import tn.esprit.auth.user.model.User;
import tn.esprit.auth.user.model.UserDetailsImpl;
import tn.esprit.auth.user.repository.UserRepository;



@Component
public class TokenAuthentication implements UserAuthenticationService{
	
	
    private final TokenService tokenService;
    private final UserRepository userRepository;
    
    @Autowired
    public TokenAuthentication(TokenService tokenService, UserRepository userRepository) {
            super();
            this.tokenService = tokenService;
            this.userRepository = userRepository;
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  

    
    @Override
    public Optional<String> login(String username, String password) {
            // TODO Auto-generated method stub
            return Optional.ofNullable(userRepository.findByUserDetails_Username(username))
                            .filter(user -> encoder.matches(password, user.get().getUserDetails().getPassword()) )
                            .map(user -> tokenService.expiring(ImmutableMap.of("username",username)));
    }
   
    @Override
    public User findByToken(String token) {
            // TODO Auto-generated method stub
        	System.out.println(token);
        
            Map<String, String> result = tokenService.verify(token);
            return userRepository.findByUserDetails_Username(result.get("username")).get();

    }
   
    @Override
    public void logout(UserDetailsImpl userDetails) {
            // TODO Auto-generated method stub
            
    }

}
