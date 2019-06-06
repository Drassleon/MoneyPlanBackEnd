package pe.edu.upc.moneyplan.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class ClientControllerApi {
	@Autowired
	IClientService clientService = new ClientService();

	IUserSecService securityService = new UserSecService();
	/*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

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

	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Client client, @RequestBody UserSec user) {

		Boolean clientDuplicate = clientService.validateDuplicate(client.getEmail());
		if (clientDuplicate) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists!");
		}
		/*String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);*/

		clientService.save(client);
		securityService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId())
				.toUri();

		return ResponseEntity.created(location).build();
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
