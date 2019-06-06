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

import pe.edu.upc.moneyplan.models.entity.Transaction;
import pe.edu.upc.moneyplan.service.impl.TransactionService;
import pe.edu.upc.moneyplan.service.inter.ITransactionService;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class TransactionControllerApi {
	@Autowired
	ITransactionService transactionService = new TransactionService();

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
	public ResponseEntity<Object> create(@RequestBody Transaction transaction) {

		transactionService.save(transaction);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(transaction.getId()).toUri();

		return ResponseEntity.created(location).build();

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
