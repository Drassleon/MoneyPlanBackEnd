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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.moneyplan.models.entity.CustomCategory;
import pe.edu.upc.moneyplan.service.impl.CustomCategoryService;
import pe.edu.upc.moneyplan.service.inter.ICustomCategoryService;

@RestController
@RequestMapping("/api/customCategory")
@CrossOrigin( 
methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS }, 
allowedHeaders = { "Content-Type", "Authorization", "Access-Control-Allow-Headers", "X-Requested-With","Access-Control-Max-Age", "Access-Control-Allow-Methods", "Access-Control-Allow-Origin" }, 
allowCredentials = "true")
public class CustomCategoryControllerApi {
	@Autowired
	ICustomCategoryService customCategoryService = new CustomCategoryService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<CustomCategory> findAll() {
		List<CustomCategory> customCategories = customCategoryService.findAll();

		return customCategories;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CustomCategory findOne(@PathVariable("id") Long id) {
		return customCategoryService.findById(id);
	}

	@RequestMapping(value = "/client/{clientId}", method = RequestMethod.GET)
	@ResponseBody
	public List<CustomCategory> findByClientId(@PathVariable("clientId") Long clientId) {
		return customCategoryService.findByClientId(clientId);
	}
	@RequestMapping(value="/client/{clientId}/search/{categoryName}",method=RequestMethod.GET)
	@ResponseBody
	public CustomCategory findByName(@PathVariable("clientId") Long clientId,@PathVariable("categoryName")String categoryName) {
		CustomCategory customCategoryFound = customCategoryService.findByName(categoryName, clientId);
		if(customCategoryFound==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!");
		}
		return customCategoryFound;
	}
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody CustomCategory customCategory) {

		if(customCategoryService.isDuplicate(customCategory.getName(), customCategory.getClient().getId()))
		{
			throw new HttpClientErrorException(HttpStatus.CONFLICT,"Category already exists!");
		}
		customCategoryService.save(customCategory);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customCategory.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody CustomCategory customCategory, @PathVariable long id) {

		CustomCategory customCategoryAux = customCategoryService.findById(id);
		if (customCategoryAux != null) {
			customCategory.setId(id);
			customCategoryService.save(customCategory);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		customCategoryService.deleteById(id);
	}

}
