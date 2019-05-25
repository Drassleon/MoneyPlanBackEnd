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

import models.entity.CategoriaPredefinida;
import service.impl.CategoriaPredefinidaService;
import service.inter.ICategoriaPredefinidaService;
@RestController
@RequestMapping("api/categoriaPredefinida")
public class CategoriaPredefinidaControllerApi {
	@Autowired
	ICategoriaPredefinidaService categoriaPredefinidaService=new CategoriaPredefinidaService();
	

	@RequestMapping(value="/",method = RequestMethod.GET)
	   @ResponseBody
	   public List<CategoriaPredefinida> findAll() {
	       List<CategoriaPredefinida> categoriaPredefinidas= categoriaPredefinidaService.findAll();
	    
	       return categoriaPredefinidas;
	   }
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	   @ResponseBody
	   public CategoriaPredefinida findOne(@PathVariable("id") Long id) {
	       return categoriaPredefinidaService.findById( id );
	   }
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody CategoriaPredefinida categoriaPredefinida) {
		
		categoriaPredefinidaService.save(categoriaPredefinida);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaPredefinida.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody CategoriaPredefinida categoriaPredefinida, @PathVariable long id) {

		CategoriaPredefinida categoriaPredefinidaAux=categoriaPredefinidaService.findById(id);
		if(categoriaPredefinidaAux!=null)
		{
			categoriaPredefinida.setId(id);
			categoriaPredefinidaService.save(categoriaPredefinida);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		categoriaPredefinidaService.deleteById(id);
	   }

}
