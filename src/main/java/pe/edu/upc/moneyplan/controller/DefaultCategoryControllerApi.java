package pe.edu.upc.moneyplan.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.moneyplan.models.entity.DefaultCategory;
import pe.edu.upc.moneyplan.service.impl.DefaultCategoryService;
import pe.edu.upc.moneyplan.service.inter.IDefaultCategoryService;

@RestController
@RequestMapping("/api/defaultCategory")
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT }, allowedHeaders = {"Content-Type", "Authorization"}, allowCredentials="true")
public class DefaultCategoryControllerApi {
	@Autowired
	IDefaultCategoryService defaultCategoryService = new DefaultCategoryService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<DefaultCategory> findAll() {
		List<DefaultCategory> defaultCategories = defaultCategoryService.findAll();

		return defaultCategories;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public DefaultCategory findOne(@PathVariable("id") Long id) {
		return defaultCategoryService.findById(id);
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody DefaultCategory defaultCategory) {

		defaultCategoryService.save(defaultCategory);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(defaultCategory.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody DefaultCategory defaultCategory, @PathVariable long id) {

		DefaultCategory defaultCategoryAux = defaultCategoryService.findById(id);
		if (defaultCategoryAux != null) {
			defaultCategory.setId(id);
			defaultCategoryService.save(defaultCategory);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		defaultCategoryService.deleteById(id);
	}

}
