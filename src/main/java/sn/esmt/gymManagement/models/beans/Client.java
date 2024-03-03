package sn.esmt.gymManagement.models.beans;

import java.util.ArrayList;
import java.util.Date;
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
import sn.esmt.gymManagement.models.beans.enums.GenderType;
import sn.esmt.gymManagement.models.beans.enums.TypePiece;

@Entity(name = "T_Client")
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
	
	@Column(name = "id_piece")
	private String identifierPiece;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	private String address;

	private Date dateOfBirth;

	private boolean firstConnection;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Souscription> subscribeList = new ArrayList<>();
	
	public Client() {}

	public Client(String firstName, String lastName, GenderType gender, TypePiece piece, String identifierPiece,
			String email, String password, String phoneNumber, java.util.Date dateOfBirth) {
		
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setGender(gender);
		this.setPiece(piece);
		this.setIdentifierPiece(identifierPiece);
		this.setEmail(email);
		this.setPassword(password);
		this.setPhoneNumber(phoneNumber);
		this.setDateOfBirth(dateOfBirth);
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
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public List<Souscription> getSubscribeList() {
		return new ArrayList<>(this.subscribeList);
	}

	public void setSubscribeList(List<Souscription> listSouscription) {
		this.subscribeList = listSouscription;
	}
	
	public void addSubscribe(Souscription souscription) {
		this.subscribeList.add(souscription);
	}
	
	public void removeSubscribe(Souscription souscription) {
		this.subscribeList.remove(souscription);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return firstName + " " + lastName;
	}

	public boolean isFirstConnection() {
		return firstConnection;
	}

	public void setFirstConnection(boolean firstConnection) {
		this.firstConnection = firstConnection;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}
