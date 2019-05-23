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

import models.entity.Recompensa;
import service.impl.RecompensaService;
import service.inter.IRecompensaService;
@RestController
@RequestMapping("api/recompensa")
public class RecompensaControllerApi {
	@Autowired
	IRecompensaService recompensaService=new RecompensaService();
	

	@RequestMapping(value="/",method = RequestMethod.GET)
	   @ResponseBody
	   public List<Recompensa> findAll() {
	       List<Recompensa> recompensas= recompensaService.findAll();
	    
	       return recompensas;
	   }
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	   @ResponseBody
	   public Recompensa findOne(@PathVariable("id") Long id) {
	       return recompensaService.findById( id );
	   }
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Recompensa recompensa) {
		
		recompensaService.save(recompensa);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(recompensa.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Recompensa recompensa, @PathVariable long id) {

		Recompensa recompensaAux=recompensaService.findById(id);
		if(recompensaAux!=null)
		{
			recompensa.setId(id);
			recompensaService.save(recompensa);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		recompensaService.deleteById(id);
	   }

}
