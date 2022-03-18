package tn.esprit.auth.user.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import tn.esprit.auth.entity.Commande;
import tn.esprit.auth.entity.Feedback;
import tn.esprit.auth.entity.Livre;
import tn.esprit.auth.entity.Offre;
import tn.esprit.auth.entity.Panier;
import tn.esprit.auth.entity.Promo;
import tn.esprit.auth.entity.Reclamation;
import tn.esprit.auth.entity.WishList;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;
	
	private String firstName;
	private String lastName;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Livre> livres;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Offre> offres;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Promo> promos;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Feedback> feedbacks;
	
	@OneToOne
	private WishList wishList;
	
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private Panier panier;
	
	@OneToMany( targetEntity=Commande.class,fetch = FetchType.LAZY, mappedBy="user" )
    private List<Commande> commandes = new ArrayList<>();
	
	@OneToMany( targetEntity=Reclamation.class,fetch = FetchType.LAZY, mappedBy="user" )
    private List<Reclamation> reclamation = new ArrayList<>();

	public User() {
	}

	public User(Long id) {
		super();
		this.id = id;
	}



	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
	

	public User(@NotBlank @Size(max = 20) String username, 
			@NotBlank @Size(max = 50) @Email String email, String firstName, String lastName,@NotBlank @Size(max = 120) String password) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Livre> getLivres() {
		return livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public WishList getWishList() {
		return wishList;
	}

	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}
	
	public List<Commande> getCommandes() {
        return commandes;
    }

	public List<Promo> getPromos() {
		return promos;
	}

	public void setPromos(List<Promo> promos) {
		this.promos = promos;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Reclamation> getReclamation() {
		return reclamation;
	}

	public void setReclamation(List<Reclamation> reclamation) {
		this.reclamation = reclamation;
	}
	
	
	
	

	
}
