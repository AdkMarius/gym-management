package sn.esmt.gymManagement.models.beans;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "T_Souscription")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@DiscriminatorValue("null")
public class Souscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	protected LocalDateTime startDate;
	
	protected Souscription() {
		this.setStartDate();
	}	
	
	public int getId() {
		return id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	protected void setStartDate() {
		this.startDate = LocalDateTime.now();
	}
}
