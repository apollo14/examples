package pl.js.gpw.domain;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class TransactionsContainer {
	private Path path;
	private List<Transaction> transactions = new ArrayList<>();
	private TransactionListValidator tv = new TransactionListValidator();
	private Map<Integer, Transaction> mergedTransactions= new HashMap<Integer, Transaction>();
	
	
	public void add(Transaction transaction) {
		transactions.add(transaction);
		merge(transaction);
	}
	
	public void mergeAll() {
		mergedTransactions = new HashMap<Integer, Transaction>();
		for(Transaction t : transactions) {
			merge(t);
		}
	}
	
	private void merge(Transaction t) {
		if (mergedTransactions.containsKey(t.getNumber())) {
			Transaction merged = mergedTransactions.get(t.getNumber()).merge(t);
			mergedTransactions.put(merged.getNumber(), merged);
		} else {
			mergedTransactions.put(t.getNumber(), t);
		}		
	}
	
	public void validate() {
		tv.populate(transactions, mergedTransactions.values());
		tv.validate();
	}
}
