package tn.esprit.auth.user.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	



	@JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetailsImpl userDetails;
	
	@ManyToMany
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	
    public User() {
        
    }
    
    
    public User(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
}

    public User(Long id, UserDetailsImpl userDetails, Set<Role> roles) {
		super();
		this.id = id;
		this.userDetails = userDetails;
		this.roles = roles;
	}
    
    


	public User(UserDetailsImpl userDetails, Set<Role> roles) {
		super();
		this.userDetails = userDetails;
		this.roles = roles;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
    
    

/*
	public long getId() {
	        return id;
	}*/



	public UserDetailsImpl getUserDetails() {
	        return userDetails;
	}
	
	public void setUserDetails(UserDetailsImpl userDetails) {
	        this.userDetails = userDetails;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}







	
	
	
	
	
}