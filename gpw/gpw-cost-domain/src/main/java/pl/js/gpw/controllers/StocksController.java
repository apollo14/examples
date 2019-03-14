package pl.js.gpw.controllers;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.js.gpw.controllers.results.TransactionCollectionResult;
import pl.js.gpw.domain.StockParams;
import pl.js.gpw.service.TransactionService;

@RestController
@RequestMapping("/stocks")
public class StocksController {
	@Autowired
	private TransactionService transactionService;

	@GetMapping("")
	public Collection<String> getStockNames(){
		return transactionService.getAllStockNames();
	}
	
	@GetMapping("/params")
	public Collection<StockParams> getParams(){
		return transactionService.getParams();
	}
	
	@GetMapping("/{name}/params")
	public StockParams getParamsForName(@PathVariable("name") String name) {
		return transactionService.getParamsForName(name);
	}
	
	@GetMapping("/{name}/cost")
	public BigDecimal getCostForName(@PathVariable("name") String name) {
		return transactionService.getCostForName(name);
	}

	@GetMapping("/{name}/netCost")
	public BigDecimal getNetCostForName(@PathVariable("name") String name) {
		return transactionService.getNetCostForName(name);
	}

	@GetMapping("/{name}/price")
	public BigDecimal getPriceForName(@PathVariable("name") String name) {
		return transactionService.getBuyPrice(name);
	}

	@GetMapping("/{name}/transactions")
	public TransactionCollectionResult getTransactionsForName(@PathVariable("name") String name) {
		TransactionCollectionResult result = new TransactionCollectionResult();
		result.setData(transactionService.getTransactionsForNameSorted(name));
		return result;		
	}

	@GetMapping("/{name}/transactions/{type}")
	public TransactionCollectionResult getTransactionsForNameAndType(
			@PathVariable("name") String name,
			@PathVariable("type") String type
			) {
		TransactionCollectionResult result = new TransactionCollectionResult();
		result.setData(transactionService.getTransactionsForNameAndTypeSorted(name, type));
		return result;		
	}
	
	

}
