package sn.esmt.gymManagement.models.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import sn.esmt.gymManagement.models.beans.enums.TypeRole;

@Entity(name = "T_Role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private int id;
	
	@Enumerated(EnumType.STRING)
	private TypeRole role;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Privilege> listPrivilege = new ArrayList<>();
	
	public Role() {}

	public Role(TypeRole role, List<Privilege> privileges) {
		this.setRole(role);
		this.setListPrivilege(privileges);
	}

	public int getId() {
		return id;
	}

	public List<Privilege> getListPrivilege() {
		return new ArrayList<Privilege>(this.listPrivilege);
	}

	public void setListPrivilege(List<Privilege> listPrivilege) {
		this.listPrivilege = listPrivilege;
	}
	
	public void addPrivilege(Privilege p) {
		this.listPrivilege.add(p);
	}
	
	public void removePrivilege(Privilege p) {
		this.listPrivilege.remove(p);
	}

	public TypeRole getRole() {
		return role;
	}

	public void setRole(TypeRole role) {
		this.role = role;
	}
	
	public boolean check(Privilege privilege) {
		return (this.listPrivilege.contains(privilege)) ? true : false;
	}
}
