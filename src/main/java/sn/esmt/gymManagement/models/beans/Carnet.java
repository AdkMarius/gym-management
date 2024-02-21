 package sn.esmt.gymManagement.models.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("CAR")
public class Carnet extends Souscription{
	
	private double priceOfCarnet;
	
	private int sessionNumber;
	
	@Column(name = "eNumber")
	private int effectifSessionNumber = 0;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Paiement> paiymentList;
	
	private static final int SESSION_PRICE = 1000;
	
	public Carnet() {}	
	
	public Carnet(int sessionNumber) {
		super();
		this.setSessionNumber(sessionNumber);
		this.setPriceOfCarnet();
		this.setEffectifSessionNumber();
	}
	
	public double getPriceOfCarnet() {
		return priceOfCarnet;
	}
	
	private void setPriceOfCarnet() {
		this.priceOfCarnet = SESSION_PRICE * this.sessionNumber;
	}
	
	public int getSessionNumber() {
		return sessionNumber;
	}
	
	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}
	
	public int getEffectifSessionNumber() {
		return effectifSessionNumber;
	}
	
	public void setEffectifSessionNumber() {
		this.effectifSessionNumber++;
	}
	
	public List<Paiement> getPaiymentList() {
		return new ArrayList<Paiement>(this.paiymentList);
	}
	
	public void setPaiymentList(List<Paiement> listPaiement) {
		this.paiymentList = listPaiement;
	}
	
	public void addPaiement(Paiement paiement) {
		this.paiymentList.add(paiement);
	}
	
	public void removePaiemebt(Paiement paiement) {
		this.paiymentList.remove(paiement);
	}
	
}
