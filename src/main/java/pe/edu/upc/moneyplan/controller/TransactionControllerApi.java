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
import pe.edu.upc.moneyplan.models.entity.Transaction;
import pe.edu.upc.moneyplan.service.impl.ClientService;
import pe.edu.upc.moneyplan.service.impl.CustomCategoryService;
import pe.edu.upc.moneyplan.service.impl.DefaultCategoryService;
import pe.edu.upc.moneyplan.service.impl.TransactionService;
import pe.edu.upc.moneyplan.service.inter.IClientService;
import pe.edu.upc.moneyplan.service.inter.ICustomCategoryService;
import pe.edu.upc.moneyplan.service.inter.IDefaultCategoryService;
import pe.edu.upc.moneyplan.service.inter.ITransactionService;
import pe.edu.upc.utils.TransactionDTO;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(
methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS }, 
allowedHeaders = { "Content-Type", "Authorization", "Access-Control-Allow-Headers", "X-Requested-With","Access-Control-Max-Age", "Access-Control-Allow-Methods", "Access-Control-Allow-Origin" }, 
allowCredentials = "true")
public class TransactionControllerApi {
	@Autowired
	ITransactionService transactionService = new TransactionService();
	@Autowired
	IDefaultCategoryService defaultCategoryService = new DefaultCategoryService();
	@Autowired
	ICustomCategoryService customCategoryService = new CustomCategoryService();
	@Autowired
	IClientService clientService = new ClientService();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Transaction> findAll() {
		List<Transaction> transactions = transactionService.findAll();

		return transactions;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Transaction findOne(@PathVariable("id") Long id) {
		return transactionService.findById(id);
	}

	@RequestMapping(value = "/client/{clientId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Transaction> findByClientId(@PathVariable("clientId") Long clientId) {
		return transactionService.findByClientId(clientId);
	}

	@RequestMapping(value = "/resumen/gasto/promedio/{clientId}/{month}")
	@ResponseBody
	public double ExpenseAverage(@PathVariable("clientId") Long clientId, @PathVariable("month") int month) {
		double averageSpent = 0.00f;
		List<Transaction> expenses = transactionService.findByClientId(clientId);
		for (Transaction expense : expenses) {
			if (expense.getTransactionType() == 1)
				averageSpent += expense.getAmount();
		}
		return averageSpent / expenses.size();
	}

	@PostMapping("/")
	public HttpStatus create(@RequestBody TransactionDTO transaction) {
		Client client = clientService.findById(transaction.getClientId());
		if(client==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found"); 
		}
		Transaction t = new Transaction();
		t.setAmount(transaction.getAmount());
		t.setClient(client);
		t.setDescription(transaction.getDescription());
		t.setTimestamp(transaction.getTimestamp());
		t.setTransactionType(transaction.getTransactionType());
		DefaultCategory defaultCategory = defaultCategoryService.findByName(transaction.getCategoryName());
		if(defaultCategory!=null)
		{
			t.setDefaultCategory(defaultCategory);
		}
		else {
			CustomCategory customCategory = customCategoryService.findByName(transaction.getCategoryName(), client.getId());
			if(customCategory!=null)
			{
				t.setCustomCategory(customCategory);
			}
			else
			{
				customCategory = new CustomCategory();
				customCategory.setClient(client);
				customCategory.setName(transaction.getCategoryName());
				customCategory.setDescription("");
				customCategoryService.save(customCategory);
				t.setCustomCategory(customCategory);
			}
		}
		transactionService.save(t);

		return HttpStatus.OK;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Transaction transaction, @PathVariable long id) {

		Transaction transactionAux = transactionService.findById(id);
		if (transactionAux != null) {
			transaction.setId(id);
			transactionService.save(transaction);
			return ResponseEntity.noContent().build();

		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		transactionService.deleteById(id);
	}

}
