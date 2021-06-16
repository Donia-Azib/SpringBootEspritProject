package tn.esprit.livre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offre {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reference;
	private float pourcentage;
	private String date_debut;
	private String date_fin;
	private int quantite;
	
	
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
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
	
	
	
	

}
