package sn.esmt.gymManagement.models.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;

@Entity(name = "T_Utilisateur")
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private TypeUtilisateur userType;

	private boolean active;

//	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Role> listRole = new ArrayList<Role>();
	
	public Utilisateur() {}	
	
	public Utilisateur(String firstName, String lastName, String email, String password, TypeUtilisateur userType, List<Role> listRole) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setUserType(userType);
		this.setListRole(listRole);
	}

	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public TypeUtilisateur getUserType() {
		return userType;
	}
	
	public void setUserType(TypeUtilisateur userType) {
		this.userType = userType;
	}

	public List<Role> getListRole() {
		return new ArrayList<Role>(this.listRole);
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}
	
	public void addRole(Role role) {
		this.listRole.add(role);
	}
	
	public void removeRole(Role role) {
		this.listRole.remove(role);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserName() {
		return this.getFirstName() + " " + this.getLastName();
	}
}
