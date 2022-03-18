package tn.esprit.auth.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.auth.user.model.User;


@Entity
public class Reclamation implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reference;
	private String description;
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commande_reference", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Commande commande;
	
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name="user_id", nullable=false)
	 private User user;
	
	public Reclamation(Commande commande, Long reference, String description, String type) {
		super();
		this.reference = reference;
		this.description = description;
		this.type = type;
		this.setCommande( commande );
	}

	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getReference() {
		return reference;
	}
	public void setReference(Long id) {
		this.reference = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
	
	

}
