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

import pe.edu.upc.moneyplan.models.entity.Reward;
import pe.edu.upc.moneyplan.service.impl.RewardService;
import pe.edu.upc.moneyplan.service.inter.IRewardService;

@RestController
@RequestMapping("api/reward")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class RewardControllerApi {
	@Autowired
	IRewardService rewardService = new RewardService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Reward> findAll() {
		List<Reward> rewards = rewardService.findAll();

		return rewards;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Reward findOne(@PathVariable("id") Long id) {
		return rewardService.findById(id);
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Reward reward) {

		rewardService.save(reward);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reward.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Reward reward, @PathVariable long id) {

		Reward rewardAux = rewardService.findById(id);
		if (rewardAux != null) {
			reward.setId(id);
			rewardService.save(reward);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		rewardService.deleteById(id);
	}

}
