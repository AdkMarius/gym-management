package sn.esmt.gymManagement.models.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import sn.esmt.gymManagement.models.beans.enums.GenderType;
import sn.esmt.gymManagement.models.beans.enums.TypePiece;

@Entity(name = "t_client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private GenderType gender;
	
	@Enumerated(EnumType.STRING)
	private TypePiece piece;
	
	private String identifierPiece;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private LocalDate dateOfBirth;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
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
	
	public GenderType getGender() {
		return gender;
	}
	
	public void setGender(GenderType gender) {
		this.gender = gender;
	}
	
	public TypePiece getPiece() {
		return piece;
	}
	
	public void setPiece(TypePiece piece) {
		this.piece = piece;
	}
	
	public String getIdentifierPiece() {
		return identifierPiece;
	}
	
	public void setIdentifierPiece(String identifierPiece) {
		this.identifierPiece = identifierPiece;
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public List<Souscription> getSubscribeList() {
		return subscribeList;
	}

	public void setSubscribeList(List<Souscription> subscribeList) {
		this.subscribeList = subscribeList;
	}
	
}
