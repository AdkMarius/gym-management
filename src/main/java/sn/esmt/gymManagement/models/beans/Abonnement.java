package sn.esmt.gymManagement.models.beans;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import sn.esmt.gymManagement.models.beans.enums.TypeAbonnement;

@Entity(name = "t_abonnement")
public class Abonnement extends Souscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_abonnement")
	private int id;
	
	private double subscribeMontant;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	@Enumerated(EnumType.STRING)
	private TypeAbonnement subscribeType;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getSubscribeMontant() {
		return subscribeMontant;
	}
	
	public void setSubscribeMontant(double subscribeMontant) {
		this.subscribeMontant = subscribeMontant;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public TypeAbonnement getSubscribeType() {
		return subscribeType;
	}

	public void setSubscribeType(TypeAbonnement subscribeType) {
		this.subscribeType = subscribeType;
	}
	
	
}
