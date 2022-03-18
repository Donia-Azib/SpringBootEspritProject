package tn.esprit.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.auth.user.model.User;


@Entity
public class Offre implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reference;
	private float pourcentage;
	private String date_debut;
	private String date_fin;
	private int quantite;
	private boolean diponibilite;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "offre")
	@JsonIgnore
	private List<Livre> livres = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	public Offre(Long reference, float pourcentage, String date_debut, String date_fin, int quantite,
			boolean diponibilite, List<Livre> livres, User user) {
		super();
		this.reference = reference;
		this.pourcentage = pourcentage;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.quantite = quantite;
		this.diponibilite = diponibilite;
		this.livres = livres;
		this.user = user;
	}


	public Offre() {
	}

	public Long getReference() {
		return reference;
	}
	public void setReference(Long reference) {
		this.reference = reference;
	}
	public float getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(float pourcentage) {
		this.pourcentage = pourcentage;
	}
	public String getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}
	public String getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public List<Livre> getLivres() {
		return livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDiponibilite() {
		return diponibilite;
	}

	public void setDiponibilite(boolean diponibilite) {
		this.diponibilite = diponibilite;
	}
	
	
	
	

}
