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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Security")
public class UserSec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@NotNull
	@Column(name = "UserName")
	private String username;

	@NotNull
	@Column(name = "Password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "Client_id")
	private Client client;


	public UserSec(Long id, @NotNull String username, @NotNull String password, Client client) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.client = client;
	}
	public UserSec() {}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
