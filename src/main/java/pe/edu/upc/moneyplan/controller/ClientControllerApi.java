package pe.edu.upc.moneyplan.controller;

import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.moneyplan.models.entity.Client;
import pe.edu.upc.moneyplan.models.entity.UserSec;
import pe.edu.upc.moneyplan.service.impl.ClientService;
import pe.edu.upc.moneyplan.service.impl.UserSecService;
import pe.edu.upc.moneyplan.service.inter.IClientService;
import pe.edu.upc.moneyplan.service.inter.IUserSecService;
import pe.edu.upc.utils.UserDTO;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:4200", 
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS }, 
		allowedHeaders = { "Content-Type", "Authorization", "Access-Control-Allow-Headers", "X-Requested-With","Access-Control-Max-Age", "Access-Control-Allow-Methods", "Access-Control-Allow-Origin" }, 
		allowCredentials = "true")
public class ClientControllerApi {
	@Autowired
	IClientService clientService = new ClientService();
	@Autowired
	IUserSecService securityService = new UserSecService();
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Client> findAll() {
		List<Client> clients = clientService.findAll();

		return clients;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Client findOne(@PathVariable("id") Long id) {
		return clientService.findById(id);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	@ResponseBody
	public Long findClientByUsername(String username) {
		UserSec userFound = securityService.findByUserName(username);
		Client clientFound = clientService.findByUserId(userFound.getId());
		return clientFound.getId();
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody UserDTO user) {

		Client client = user.getClient();
		UserSec userSec = user.getUser();

		Boolean clientDuplicate = clientService.validateDuplicate(client.getEmail());
		if (clientDuplicate) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists!");
		}
		if (securityService.findByUserName(userSec.getUsername()) != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists!");
		}
		String encodedPassword = bCryptPasswordEncoder.encode(userSec.getPassword());
		userSec.setPassword(encodedPassword);
		try {
			securityService.save(userSec);
		} finally {
			userSec = securityService.findByUserName(user.getUser().getUsername());
			client.setUser(userSec);
			clientService.save(client);
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public HttpStatus login(@RequestBody UserSec user) {
		System.out.println("Entró");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		UserSec userFound = securityService.findByUserName(user.getUsername());
		byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
		String decodedString = new String(decodedBytes);
		user.setPassword(decodedString);
		if (userFound == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		if (bCryptPasswordEncoder.matches(user.getPassword(), userFound.getPassword())) {
			return HttpStatus.OK;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password doesn't match");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Client client, @PathVariable long id) {

		Client clientAux = clientService.findById(id);
		if (clientAux != null) {
			client.setId(id);
			clientService.save(client);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		clientService.deleteById(id);
	}

}
