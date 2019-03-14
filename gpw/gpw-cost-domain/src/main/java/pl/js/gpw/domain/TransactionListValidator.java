package pl.js.gpw.domain;

import java.util.Collection;

import lombok.Data;

@Data
public class TransactionListValidator {
	private String fileName;
	ValidationIndicators notMerged = new ValidationIndicators();
	ValidationIndicators merged = new ValidationIndicators();

	public void validate() {
		if (notMerged.getAmount().compareTo(merged.getAmount())
				+ notMerged.getCommission().compareTo(merged.getCommission())
				+ notMerged.getValue().compareTo(merged.getValue()) != 0) {
			throw new RuntimeException("Validation finished with error for file: " + fileName);
		}
	}

	public void populate(Collection<Transaction> nm, Collection<Transaction> m) {
		notMerged = new ValidationIndicators();
		merged = new ValidationIndicators();
		notMerged.setAmount(nm.stream().map(x -> x.getAmount()).reduce((x, y) -> x.add(y)).get());
		notMerged.setCommission(nm.stream().map(x -> x.getCommission()).reduce((x, y) -> x.add(y)).get());
		notMerged.setValue(nm.stream().map(x -> x.getValue()).reduce((x, y) -> x.add(y)).get());
		merged.setAmount(m.stream().map(x -> x.getAmount()).reduce((x, y) -> x.add(y)).get());
		merged.setCommission(m.stream().map(x -> x.getCommission()).reduce((x, y) -> x.add(y)).get());
		merged.setValue(m.stream().map(x -> x.getValue()).reduce((x, y) -> x.add(y)).get());
	}
}
