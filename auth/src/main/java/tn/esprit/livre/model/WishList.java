package tn.esprit.livre.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class WishList {
	
	@Id
	private Long id;
	private int nombreDeLivre;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNombreDeLivre() {
		return nombreDeLivre;
	}
	public void setNombreDeLivre(int nombreDeLivre) {
		this.nombreDeLivre = nombreDeLivre;
	}
	
	
	
	
	

}
