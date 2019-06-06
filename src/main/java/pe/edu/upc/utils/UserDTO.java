package pe.edu.upc.utils;

import java.io.Serializable;

import pe.edu.upc.moneyplan.models.entity.Client;
import pe.edu.upc.moneyplan.models.entity.UserSec;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Client client;
	private UserSec user;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public UserSec getUser() {
		return user;
	}

	public void setUser(UserSec user) {
		this.user = user;
	}

}
