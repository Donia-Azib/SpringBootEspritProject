package tn.esprit.demande.service;

import tn.esprit.auth.user.model.UserDetailsImpl;

public class DemandeRequest {

	
	private  UserDetailsImpl user;

	public DemandeRequest(UserDetailsImpl user) {
		super();
		this.user = user;
	}

	public DemandeRequest() {
		super();
	}

	public UserDetailsImpl getUser() {
		return user;
	}

	public void setUser(UserDetailsImpl user) {
		this.user = user;
	}

	
	
	
	
	
	
}
