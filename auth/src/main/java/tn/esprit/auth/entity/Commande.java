package tn.esprit.auth.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.auth.user.model.User;

@Entity
public class Commande implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String reference;
	private String date;
	private String status;
	private float total;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Livre> livres ;
	
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name="user_id", nullable=false)
	 private User user;


	
	public Commande(String reference, String date, String status, float total, List<Livre> livres) {
		super();
		this.reference = reference;
		this.date = date;
		this.status = status;
		this.total = total;
		this.livres = livres;
	}


	public Commande() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
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




	
	

}
