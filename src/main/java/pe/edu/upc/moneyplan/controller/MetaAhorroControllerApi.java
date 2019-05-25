package pe.edu.upc.moneyplan.controller;

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

import pe.edu.upc.moneyplan.models.entity.MetaAhorro;
import pe.edu.upc.moneyplan.service.impl.MetaAhorroService;
import pe.edu.upc.moneyplan.service.inter.IMetaAhorroService;
@RestController
@RequestMapping("api/metaAhorro")
public class MetaAhorroControllerApi {
	@Autowired
	IMetaAhorroService metaAhorroService=new MetaAhorroService();
	

	@RequestMapping(value="/",method = RequestMethod.GET)
	   @ResponseBody
	   public List<MetaAhorro> findAll() {
	       List<MetaAhorro> metaAhorros= metaAhorroService.findAll();
	    
	       return metaAhorros;
	   }
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	   @ResponseBody
	   public MetaAhorro findOne(@PathVariable("id") Long id) {
	       return metaAhorroService.findById( id );
	   }
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody MetaAhorro metaAhorro) {
		
		metaAhorroService.save(metaAhorro);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(metaAhorro.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody MetaAhorro metaAhorro, @PathVariable long id) {

		MetaAhorro metaAhorroAux=metaAhorroService.findById(id);
		if(metaAhorroAux!=null)
		{
			metaAhorro.setId(id);
			metaAhorroService.save(metaAhorro);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		metaAhorroService.deleteById(id);
	   }

}
