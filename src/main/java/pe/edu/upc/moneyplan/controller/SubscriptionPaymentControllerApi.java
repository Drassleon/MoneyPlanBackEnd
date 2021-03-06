package pe.edu.upc.moneyplan.controller;

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
import org.springframework.web.server.ResponseStatusException;

import pe.edu.upc.moneyplan.models.entity.Client;
import pe.edu.upc.moneyplan.models.entity.CustomCategory;
import pe.edu.upc.moneyplan.models.entity.DefaultCategory;
import pe.edu.upc.moneyplan.models.entity.SubscriptionPayment;
import pe.edu.upc.moneyplan.service.impl.ClientService;
import pe.edu.upc.moneyplan.service.impl.CustomCategoryService;
import pe.edu.upc.moneyplan.service.impl.DefaultCategoryService;
import pe.edu.upc.moneyplan.service.impl.SubscriptionPaymentService;
import pe.edu.upc.moneyplan.service.inter.IClientService;
import pe.edu.upc.moneyplan.service.inter.ICustomCategoryService;
import pe.edu.upc.moneyplan.service.inter.IDefaultCategoryService;
import pe.edu.upc.moneyplan.service.inter.ISubscriptionPaymentService;
import pe.edu.upc.utils.SubscriptionPaymentDTO;

@RestController
@RequestMapping("/api/subscriptionPayment")
@CrossOrigin(
methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS }, 
allowedHeaders = { "Content-Type", "Authorization", "Access-Control-Allow-Headers", "X-Requested-With","Access-Control-Max-Age", "Access-Control-Allow-Methods", "Access-Control-Allow-Origin" }, 
allowCredentials = "true")
public class SubscriptionPaymentControllerApi {
	@Autowired
	ISubscriptionPaymentService subscriptionPaymentService = new SubscriptionPaymentService();
	@Autowired
	IDefaultCategoryService defaultCategoryService = new DefaultCategoryService();
	@Autowired
	ICustomCategoryService customCategoryService = new CustomCategoryService();
	@Autowired
	IClientService clientService = new ClientService();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<SubscriptionPayment> findAll() {
		List<SubscriptionPayment> subscriptionPayments = subscriptionPaymentService.findAll();

		return subscriptionPayments;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SubscriptionPaymentDTO findOne(@PathVariable("id") Long id) {
		SubscriptionPayment rawSubscription = subscriptionPaymentService.findById(id);
		SubscriptionPaymentDTO subscription = new SubscriptionPaymentDTO();
		subscription.setAmount(rawSubscription.getAmount());
		
		if(rawSubscription.getCustomCategory()==null)
		{
			subscription.setCategoryName(rawSubscription.getDefaultCategory().getName());
		}
		else
		{
			subscription.setCategoryName(rawSubscription.getCustomCategory().getName());
		}
		
		subscription.setClientId(rawSubscription.getClient().getId());
		subscription.setDescription(rawSubscription.getDescription());
		subscription.setBillingDate(rawSubscription.getBillingDate());
		return subscription;
	}

	@PostMapping("/")
	public HttpStatus create(@RequestBody SubscriptionPaymentDTO subscriptionPayment) {
		Client client = clientService.findById(subscriptionPayment.getClientId());		
		if(client==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found"); 
		}
		SubscriptionPayment s = new SubscriptionPayment();

		s.setAmount(subscriptionPayment.getAmount());
		s.setBillingDate(subscriptionPayment.getBillingDate());
		s.setClient(client);
		s.setDescription(subscriptionPayment.getDescription());
		
		DefaultCategory defaultCategory = defaultCategoryService.findByName(subscriptionPayment.getCategoryName());
		if(defaultCategory!=null)
		{
			s.setDefaultCategory(defaultCategory);
		}
		else {
			CustomCategory customCategory = customCategoryService.findByName(subscriptionPayment.getCategoryName(), client.getId());
			if(customCategory!=null)
			{
				s.setCustomCategory(customCategory);
			}
			else
			{
				customCategory = new CustomCategory();
				customCategory.setClient(client);
				customCategory.setName(subscriptionPayment.getCategoryName());
				customCategory.setDescription("");
				customCategoryService.save(customCategory);
				s.setCustomCategory(customCategory);
			}
		}
		subscriptionPaymentService.save(s);

		return HttpStatus.OK;
	}
	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<SubscriptionPayment> findByClientId(@PathVariable("id") Long id) {
		List<SubscriptionPayment> subscriptions = subscriptionPaymentService.findByClientId(id);
		if(subscriptions.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Client has no subscriptions");
		}
		return subscriptions;
	}
	
	@PutMapping("/{id}")
	public HttpStatus update(@RequestBody SubscriptionPaymentDTO subscriptionPayment, @PathVariable long id) {

		Client client = clientService.findById(subscriptionPayment.getClientId());		
		if(client==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found"); 
		}
		SubscriptionPayment s = subscriptionPaymentService.findById(id);

		s.setAmount(subscriptionPayment.getAmount());
		s.setBillingDate(subscriptionPayment.getBillingDate());
		s.setClient(client);
		s.setDescription(subscriptionPayment.getDescription());
		
		DefaultCategory defaultCategory = defaultCategoryService.findByName(subscriptionPayment.getCategoryName());
		if(defaultCategory!=null)
		{
			s.setDefaultCategory(defaultCategory);
		}
		else {
			CustomCategory customCategory = customCategoryService.findByName(subscriptionPayment.getCategoryName(), client.getId());
			if(customCategory!=null)
			{
				s.setCustomCategory(customCategory);
			}
			else
			{
				customCategory = new CustomCategory();
				customCategory.setClient(client);
				customCategory.setName(subscriptionPayment.getCategoryName());
				customCategory.setDescription("");
				customCategoryService.save(customCategory);
				s.setCustomCategory(customCategory);
			}
		}
		subscriptionPaymentService.save(s);

		return HttpStatus.OK;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		subscriptionPaymentService.deleteById(id);
	}
}
