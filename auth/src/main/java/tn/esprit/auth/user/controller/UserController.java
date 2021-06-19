package tn.esprit.auth.user.controller;

import java.lang.module.FindException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
	private BCryptPasswordEncoder encoder;
    
    
    
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus register (@RequestBody UserDetailsImpl userDetails) {
            userDetails.setPassword(encoder.encode(userDetails.getPassword())); 
            userRepository.save(new User(userDetails));
            //return login(userDetails);
            return HttpStatus.CREATED;
    }
    
    
    @PostMapping(value ="/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> login(@RequestBody UserDetailsImpl userDetails) {
            
            return Collections.singletonMap("token",userAuthenticationService.login(userDetails.getUsername(),userDetails.getPassword())
                            .orElseThrow(() -> new RuntimeException("invalid login details")));
            
    }
    

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus addUser (@RequestBody UserDetailsImpl userDetails) {
            userDetails.setPassword(encoder.encode(userDetails.getPassword())); 
            userRepository.save(new User(userDetails));
            return HttpStatus.CREATED;
    }
    
    @PostMapping(value = "/del", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delUser (@RequestParam String username) {
    		User user = userRepository.findByUserDetails_Username(username).get();
            userRepository.delete(user);
            return HttpStatus.ACCEPTED;
    }
    
    
	
	

}
