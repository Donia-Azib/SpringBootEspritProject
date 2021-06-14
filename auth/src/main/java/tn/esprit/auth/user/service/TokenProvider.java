package tn.esprit.auth.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import tn.esprit.auth.user.model.User;

@Component
public class TokenProvider extends AbstractUserDetailsAuthenticationProvider {

	
    private final UserAuthenticationService userAuthenticationService;
    
    @Autowired      
    public TokenProvider(UserAuthenticationService userAuthenticationService) {
                    this.userAuthenticationService = userAuthenticationService;
    }
   
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                    UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
            // TODO Auto-generated method stub
            
    }
   
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
                    throws AuthenticationException {
            // TODO Auto-generated method stub
            final Object token = authentication.getCredentials();
            return Optional.ofNullable(token).map(String::valueOf)
                            .map(userAuthenticationService::findByToken)
                            .map(User::getUserDetails).get();
    }


}
