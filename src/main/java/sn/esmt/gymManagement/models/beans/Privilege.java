package sn.esmt.gymManagement.models.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import sn.esmt.gymManagement.models.beans.enums.TypePrivilege;

@Entity(name = "t_privilege")
public class Privilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_privilege")
	private int id;
	
	@Enumerated(EnumType.STRING)
	private TypePrivilege privilege;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public TypePrivilege getDescription() {
		return privilege;
	}
	
	public void setDescription(TypePrivilege privilege) {
		this.privilege = privilege;
	}
}
