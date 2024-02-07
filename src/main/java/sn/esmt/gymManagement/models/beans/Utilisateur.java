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

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Role> listRole = new ArrayList<Role>();
	
	public Utilisateur(String firstName, String lastName, String email, String password, TypeUtilisateur userType) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setUserType(userType);
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
}
