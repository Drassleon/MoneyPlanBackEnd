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

import models.entity.CategoriaPersonalizada;
import service.impl.CategoriaPersonalizadaService;
import service.inter.ICategoriaPersonalizadaService;

@RestController
@RequestMapping("/api/categoriaPersonalizada")
public class CategoriaPersonalizadaControllerApi {
	@Autowired
	ICategoriaPersonalizadaService categoriaPersonalizadaService=new CategoriaPersonalizadaService();
	

	@RequestMapping(value="/",method = RequestMethod.GET)
	   @ResponseBody
	   public List<CategoriaPersonalizada> findAll() {
	       List<CategoriaPersonalizada> categoriaPersonalizadas= categoriaPersonalizadaService.findAll();
	    
	       return categoriaPersonalizadas;
	   }
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	   @ResponseBody
	   public CategoriaPersonalizada findOne(@PathVariable("id") Long id) {
	       return categoriaPersonalizadaService.findById( id );
	   }
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody CategoriaPersonalizada categoriaPersonalizada) {
		
		categoriaPersonalizadaService.save(categoriaPersonalizada);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaPersonalizada.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	 
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody CategoriaPersonalizada categoriaPersonalizada, @PathVariable long id) {

		CategoriaPersonalizada categoriaPersonalizadaAux=categoriaPersonalizadaService.findById(id);
		if(categoriaPersonalizadaAux!=null)
		{
			categoriaPersonalizada.setId(id);
			categoriaPersonalizadaService.save(categoriaPersonalizada);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		categoriaPersonalizadaService.deleteById(id);
	   }

}
