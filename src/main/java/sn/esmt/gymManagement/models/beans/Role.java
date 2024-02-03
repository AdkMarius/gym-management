package sn.esmt.gymManagement.models.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = "t_role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private int id;
	
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Privilege> listPrivilege = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Privilege> getListPrivilege() {
		return listPrivilege;
	}

	public void setListPrivilege(List<Privilege> listPrivilege) {
		this.listPrivilege = listPrivilege;
	}
}
