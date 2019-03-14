package pl.js.gpw.controllers.results;

import java.util.Collection;

import lombok.Data;
import pl.js.gpw.domain.Transaction;

@Data
public class TransactionCollectionResult {
	private Collection<Transaction> data;
}
