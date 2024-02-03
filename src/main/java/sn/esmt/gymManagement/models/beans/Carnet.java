package sn.esmt.gymManagement.models.beans;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "t_carnet")
public class Carnet extends Souscription{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carnet")
	private int id;
	
	private double priceOfCarnet;
	
	private int sessionNumber;
	
	private int effectifSessionNumber;
	
	@OneToMany(mappedBy = "carnet", cascade = CascadeType.PERSIST)
	private List<Paiement> paiymentList;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getPriceOfCarnet() {
		return priceOfCarnet;
	}
	
	public void setPriceOfCarnet(double priceOfCarnet) {
		this.priceOfCarnet = priceOfCarnet;
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
	
	public void setEffectifSessionNumber(int effectifSessionNumber) {
		this.effectifSessionNumber = effectifSessionNumber;
	}
	
	public List<Paiement> getPaiymentList() {
		return paiymentList;
	}
	
	public void setPaiymentList(List<Paiement> paiymentList) {
		this.paiymentList = paiymentList;
	}
	
}
