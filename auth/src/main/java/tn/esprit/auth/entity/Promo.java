package tn.esprit.auth.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.auth.user.model.User;

@Entity
public class Promo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codepromo ; 
	private String datedebut ; 
	private String datefin ; 
	private float pourcentage ;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	

	public Promo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Promo(Long id, String codepromo, String datedebut, String datefin, float pourcentage, User user) {
		super();
		this.id = id;
		this.codepromo = codepromo;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.pourcentage = pourcentage;
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodepromo() {
		return codepromo;
	}
	public void setCodepromo(String codepromo) {
		this.codepromo = codepromo;
	}

	public String getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(String datedebut) {
		this.datedebut = datedebut;
	}

	public String getDatefin() {
		return datefin;
	}

	public void setDatefin(String datefin) {
		this.datefin = datefin;
	}

	public float getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(float pourcentage) {
		this.pourcentage = pourcentage;
	} 
	
	
	
	

}
