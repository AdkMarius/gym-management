package sn.esmt.gymManagement.models.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "T_Statistique")
public class Statistique {
	
	@Id
	@Column(name = "id_stat")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "act_number")
	private int activatedAccountNumber;
	
	@Column(name = "deact_number")
	private int deactivatedAccountNumber;
	
	@Column(name = "client_sub")
	private int numberClientPerSubscribe;
	
	@Column(name = "client_carn")
	private int numberClientCarnet;
	
	public Statistique() {}
	
	public Statistique(int activatedAccountNumber) {
		this.setActivateAccountNumber(activatedAccountNumber);
	}

	public int getId() {
		return id;
	}

	public int getActivateAccountNumber() {
		return activatedAccountNumber;
	}

	public void setActivateAccountNumber(int activateAccountNumber) {
		this.activatedAccountNumber = activateAccountNumber;
	}

	public int getDeactivateAccountNumber() {
		return deactivatedAccountNumber;
	}

	public void setDeactivateAccountNumber(int deactivateAccountNumber) {
		this.deactivatedAccountNumber = deactivateAccountNumber;
	}

	public int getNumberClientPerSubscribe() {
		return numberClientPerSubscribe;
	}

	public void setNumberClientPerSubscribe(int numberClientPerSubscribe) {
		this.numberClientPerSubscribe = numberClientPerSubscribe;
	}

	public int getNumberClientCarnet() {
		return numberClientCarnet;
	}

	public void setNumberClientCarnet(int numberClientCarnet) {
		this.numberClientCarnet = numberClientCarnet;
	}
}
