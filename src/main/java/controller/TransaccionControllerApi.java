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

import models.entity.Transaccion;
import service.impl.TransaccionService;
import service.inter.ITransaccionService;

@RestController
@RequestMapping("api/transaccion")
public class TransaccionControllerApi {
	@Autowired
	ITransaccionService transaccionService=new TransaccionService();
	

	@RequestMapping(value="/",method = RequestMethod.GET)
	   @ResponseBody
	   public List<Transaccion> findAll() {
	       List<Transaccion> transaccions= transaccionService.findAll();
	    
	       return transaccions;
	   }
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	   @ResponseBody
	   public Transaccion findOne(@PathVariable("id") Long id) {
	       return transaccionService.findById( id );
	   }
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Transaccion transaccion) {
		
		transaccionService.save(transaccion);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(transaccion.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Transaccion transaccion, @PathVariable long id) {

		Transaccion transaccionAux=transaccionService.findById(id);
		if(transaccionAux!=null)
		{
			transaccion.setId(id);
			transaccionService.save(transaccion);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		transaccionService.deleteById(id);
	   }

}
