package tn.esprit.livre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Feedback {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int note;
	private String commentaire;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	

}
