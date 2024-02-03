package sn.esmt.gymManagement.models.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;

@Entity(name = "t_utilisateur")
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
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Souscription> subscribeList = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	public List<Souscription> getSubscribeList() {
		return subscribeList;
	}

	public void setSubscribeList(List<Souscription> subscribeList) {
		this.subscribeList = subscribeList;
	}
	
	
}
