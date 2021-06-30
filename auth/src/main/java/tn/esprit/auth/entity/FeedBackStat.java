package tn.esprit.auth.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeedBackStat implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int nbPositiveCommentsBook = 0;
	private int nbNegativeCommentsBook = 0;
	private int nbRejectedCommentsBook = 0;

	private int nbPositiveCommentsOffer = 0;
	private int nbNegativeCommentsOffer = 0;
	private int nbRejectedCommentsOffer = 0;

	private LocalDate date = LocalDate.now();

	public FeedBackStat() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getNbPositiveCommentsBook() {
		return nbPositiveCommentsBook;
	}

	public void setNbPositiveCommentsBook(int nbPositiveCommentsBook) {
		this.nbPositiveCommentsBook = nbPositiveCommentsBook;
	}

	public int getNbNegativeCommentsBook() {
		return nbNegativeCommentsBook;
	}

	public void setNbNegativeCommentsBook(int nbNegativeCommentsBook) {
		this.nbNegativeCommentsBook = nbNegativeCommentsBook;
	}

	public int getNbRejectedCommentsBook() {
		return nbRejectedCommentsBook;
	}

	public void setNbRejectedCommentsBook(int nbRejectedCommentsBook) {
		this.nbRejectedCommentsBook = nbRejectedCommentsBook;
	}

	public int getNbPositiveCommentsOffer() {
		return nbPositiveCommentsOffer;
	}

	public void setNbPositiveCommentsOffer(int nbPositiveCommentsOffer) {
		this.nbPositiveCommentsOffer = nbPositiveCommentsOffer;
	}

	public int getNbNegativeCommentsOffer() {
		return nbNegativeCommentsOffer;
	}

	public void setNbNegativeCommentsOffer(int nbNegativeCommentsOffer) {
		this.nbNegativeCommentsOffer = nbNegativeCommentsOffer;
	}

	public int getNbRejectedCommentsOffer() {
		return nbRejectedCommentsOffer;
	}

	public void setNbRejectedCommentsOffer(int nbRejectedCommentsOffer) {
		this.nbRejectedCommentsOffer = nbRejectedCommentsOffer;
	}

}
