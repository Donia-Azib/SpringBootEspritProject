package tn.esprit.commande.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Commande {
	
	
	@Id
	private int reference;
	private String date;
	private String status;
	private float total;
	
	
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
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
	
	
	

}
