package pl.js.gpw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.js.gpw.controllers.results.TransactionCollectionResult;
import pl.js.gpw.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("")
	public TransactionCollectionResult getAllTransactions() {
		TransactionCollectionResult result = new TransactionCollectionResult();
		result.setData(transactionService.getAllTransactions());
		return result;
	}
	
}
