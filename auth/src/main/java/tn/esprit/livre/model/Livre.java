package tn.esprit.livre.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import tn.esprit.auth.user.model.Role;

@Entity
public class Livre {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reference;
	
	private String titre;
	private String description;
	private String auteur;
	private float prix;
	private boolean disponibilite;
	private int quantite;
	
	

	
	
	public Livre(int reference, String titre, String description, String auteur, float prix, boolean disponibilite,
			int quantite) {
		super();
		this.reference = reference;
		this.titre = titre;
		this.description = description;
		this.auteur = auteur;
		this.prix = prix;
		this.disponibilite = disponibilite;
		this.quantite = quantite;
	}
	
	
	
	
	
	public Livre() {
		super();
	}





	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public boolean isDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	
	
	

}
