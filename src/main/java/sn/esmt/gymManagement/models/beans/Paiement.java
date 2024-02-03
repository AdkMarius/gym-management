package sn.esmt.gymManagement.models.beans;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "t_paiement")
public class Paiement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paiement")
	private int id;
	
	private double montant;
	
	private boolean isCarnet;
	
	private LocalDate paymentDate;
	
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
	
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public boolean isCarnet() {
		return isCarnet;
	}

	public void setCarnet(boolean isCarnet) {
		this.isCarnet = isCarnet;
	}
}
