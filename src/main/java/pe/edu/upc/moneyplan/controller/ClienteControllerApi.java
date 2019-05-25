package controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import models.entity.Cliente;
import service.impl.ClienteService;
import service.inter.IClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteControllerApi {
	@Autowired
	IClienteService clienteService=new ClienteService();
	

	@RequestMapping(value="/",method = RequestMethod.GET)
	   @ResponseBody
	   public List<Cliente> findAll() {
	       List<Cliente> clientes= clienteService.findAll();
	    
	       return clientes;
	   }
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	   @ResponseBody
	   public Cliente findOne(@PathVariable("id") Long id) {
	       return clienteService.findById( id );
	   }
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Cliente cliente) {
		
		clienteService.save(cliente);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Cliente cliente, @PathVariable long id) {

		Cliente clienteAux=clienteService.findById(id);
		if(clienteAux!=null)
		{
			cliente.setId(id);
			clienteService.save(cliente);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		clienteService.deleteById(id);
	   }

}