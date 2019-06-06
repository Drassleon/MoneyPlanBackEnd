package pe.edu.upc.moneyplan.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull
	@Column(name = "doc_id")
	private String docId;

	@NotNull
	@Column(name = "doc_id_type")
	private String docIdType;

	@NotNull
	@Column(name = "email")
	@Email
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "user_sec_id")
	private UserSec user;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocIdType() {
		return docIdType;
	}

	public void setDocIdType(String docIdType) {
		this.docIdType = docIdType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserSec getUser() {
		return user;
	}

	public void setUser(UserSec user) {
		this.user = user;
	}
}
