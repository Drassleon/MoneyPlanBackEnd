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

import pe.edu.upc.moneyplan.models.entity.SubscriptionPayment;
import pe.edu.upc.moneyplan.service.impl.SubscriptionPaymentService;
import pe.edu.upc.moneyplan.service.inter.ISubscriptionPaymentService;

@RestController
@RequestMapping("/api/subscriptionPayment")
@CrossOrigin(origins = "http://localhost:4200", 
methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS }, 
allowedHeaders = { "Content-Type", "Authorization", "Access-Control-Allow-Headers", "X-Requested-With","Access-Control-Max-Age", "Access-Control-Allow-Methods", "Access-Control-Allow-Origin" }, 
allowCredentials = "true")
public class SubscriptionPaymentControllerApi {
	@Autowired
	ISubscriptionPaymentService subscriptionPaymentService = new SubscriptionPaymentService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<SubscriptionPayment> findAll() {
		List<SubscriptionPayment> subscriptionPayments = subscriptionPaymentService.findAll();

		return subscriptionPayments;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SubscriptionPayment findOne(@PathVariable("id") Long id) {
		return subscriptionPaymentService.findById(id);
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody SubscriptionPayment subscriptionPayment) {

		subscriptionPaymentService.save(subscriptionPayment);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(subscriptionPayment.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody SubscriptionPayment subscriptionPayment, @PathVariable long id) {

		SubscriptionPayment subscriptionPaymentAux = subscriptionPaymentService.findById(id);
		if (subscriptionPaymentAux != null) {
			subscriptionPayment.setId(id);
			subscriptionPaymentService.save(subscriptionPayment);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		subscriptionPaymentService.deleteById(id);
	}
}
