package sn.esmt.gymManagement.models.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import sn.esmt.gymManagement.models.beans.enums.TypePrivilege;

@Entity(name = "T_Privilege")
public class Privilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_privilege")
	private int id;
		
	@Enumerated(EnumType.STRING)
	private TypePrivilege privilege;
	
	public Privilege() {}
	
	public Privilege(TypePrivilege privilege) {
		this.setPrivilege(privilege);
	}

	public int getId() {
		return id;
	}
	
	public TypePrivilege getPrivilege() {
		return privilege;
	}
	
	public void setPrivilege(TypePrivilege privilege) {
		this.privilege = privilege;	
	}
}
