package sn.esmt.gymManagement.models.beans;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "T_Paiement")
public class Paiement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paiement")
	private int id;
	
	private double montant;
	
	private boolean isCarnet;
	
	private LocalDateTime paymentDate;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private Utilisateur user;
	
	public Paiement() {}
	
	public Paiement(double montant, Utilisateur user) {
		this.setMontant(montant);
		this.setUser(user);
		this.setPaymentDate();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getMontant() {
		return montant;
	}
	
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	
	private void setPaymentDate() {
		this.paymentDate = LocalDateTime.now();
	}

	public boolean isCarnet() {
		return isCarnet;
	}

	public void setCarnet(boolean isCarnet) {
		this.isCarnet = isCarnet;
	}
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

}
