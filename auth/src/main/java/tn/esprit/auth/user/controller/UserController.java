package tn.esprit.auth.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.auth.user.model.User;
import tn.esprit.auth.user.model.UserDetailsImpl;
import tn.esprit.auth.user.repository.UserRepository;
import tn.esprit.auth.user.service.UserAuthenticationService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;
    

    
    @Autowired
    public UserController(UserRepository userRepository, UserAuthenticationService userAuthenticationService) {
            super();
            this.userRepository = userRepository;
            this.userAuthenticationService = userAuthenticationService;
    }
    
    
    
    @PostMapping("/register")
    public String register (@RequestBody UserDetailsImpl userDetails) {
            
            userRepository.save(new User(userDetails));
            return login(userDetails);
    }
    
    
    @PostMapping("/login")
    public String login(@RequestBody UserDetailsImpl userDetails) {
            
            return userAuthenticationService.login(userDetails.getUsername(),userDetails.getPassword())
                            .orElseThrow(() -> new RuntimeException("invalid login details"));
            
    }
	
	

}
