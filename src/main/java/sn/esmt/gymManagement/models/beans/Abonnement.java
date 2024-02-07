package sn.esmt.gymManagement.models.beans;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import sn.esmt.gymManagement.models.beans.enums.TypeAbonnement;

@Entity
@DiscriminatorValue("ABN")
public class Abonnement extends Souscription {
	
	private double subscribeMontant;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	@Enumerated(EnumType.STRING)
	private TypeAbonnement subscribeType;
	
	public Abonnement() {}	
	
	public Abonnement(TypeAbonnement subscribeType) {
		super();
		this.setSubscribeType(subscribeType);
		this.setSubscribeMontant();
		this.setEndDate();
	}
	
	public double getSubscribeMontant() {
		return subscribeMontant;
	}
	
	private void setSubscribeMontant() {
		this.subscribeMontant = defineSubscribePrice();
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	private void setEndDate() {
		this.endDate = this.startDate.plusMonths(this.subscribeType.getValue());
	}

	public TypeAbonnement getSubscribeType() {
		return subscribeType;
	}

	public void setSubscribeType(TypeAbonnement subscribeType) {
		this.subscribeType = subscribeType;
	}
	
	private int defineSubscribePrice() {
		int subscribePrice = 0;
		
		switch(this.subscribeType) {
			case MENSUEL : 
				subscribePrice = 25000;
				break;
			case TRIMESTRIEL : 
				subscribePrice = 60000;
				break;
			case ANNUEL :
				subscribePrice = 275000;
				break;
		}
		
		return subscribePrice;

	}
}
