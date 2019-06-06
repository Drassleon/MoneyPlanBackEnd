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

import pe.edu.upc.moneyplan.models.entity.SavingGoal;
import pe.edu.upc.moneyplan.service.impl.SavingGoalService;
import pe.edu.upc.moneyplan.service.inter.ISavingGoalService;

@RestController
@RequestMapping("/api/savingGoal")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class SavingGoalControllerApi {
	@Autowired
	ISavingGoalService savingGoalService = new SavingGoalService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<SavingGoal> findAll() {
		List<SavingGoal> savingGoals = savingGoalService.findAll();

		return savingGoals;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SavingGoal findOne(@PathVariable("id") Long id) {
		return savingGoalService.findById(id);
	}
	
	@RequestMapping(value = "/client/{clientId}", method = RequestMethod.GET)
	@ResponseBody
	public List<SavingGoal> findByClientId(@PathVariable("clientId") Long clientId) {
		return savingGoalService.findByClientId(clientId);
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody SavingGoal savingGoal) {

		savingGoalService.save(savingGoal);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savingGoal.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody SavingGoal savingGoal, @PathVariable long id) {

		SavingGoal savingGoalAux = savingGoalService.findById(id);
		if (savingGoalAux != null) {
			savingGoal.setId(id);
			savingGoalService.save(savingGoal);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		savingGoalService.deleteById(id);
	}

}
