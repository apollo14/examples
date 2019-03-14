package pl.js.gpw.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.js.gpw.domain.DataContainer;
import pl.js.gpw.domain.StockParams;
import pl.js.gpw.domain.Transaction;

@Service
public class TransactionService {
	@Autowired
	private DataContainer data;
	
	public Collection<Transaction> getAllTransactions(){
		return sortByTransactionNumber(data.getTransactions().values().stream());
	}
	public Collection<Transaction> getTransactionsForNameSorted(String name){
		return sortByTransactionNumber(getTransactionsForName(name).stream());
	}
	
	public Collection<Transaction> getTransactionsForNameAndTypeSorted(String name, String type){
		return sortByTransactionNumber(getTransactionsForNameAndType(name, type).stream());
	}
	
	private Collection<Transaction> getTransactionsForName(String name){
		return data.getTransactions().values().stream()
				.filter(t->t.getName().equals(name))
				.collect(Collectors.toList());
	}
	
	private Collection<Transaction> getTransactionsForNameAndType(String name, String type){
		return getTransactionsForName(name).stream()
				.filter(t->t.getType().toLowerCase().equals(type))
				.collect(Collectors.toList());
	}
		
	private Collection<Transaction> sortByTransactionNumber(Stream<Transaction> transactions) {
		return transactions
				.sorted((f1, f2)-> Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getNumber).compare(f1, f2))
				.collect(Collectors.toList());
	}
	
	public BigDecimal getCostForName(String name) {
		BigDecimal buy = getTransactionsForNameAndTypeSorted(name, "kupno").stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal sell = getTransactionsForNameAndTypeSorted(name, "sprzedaż").stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
		return sell.add(buy.negate());
	}
	
	public BigDecimal getNetCostForName(String name) {
		BigDecimal buy = getTransactionsForNameAndTypeSorted(name, "kupno").stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal sell = getTransactionsForNameAndTypeSorted(name, "sprzedaż").stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		return sell.add(buy.negate());
	}
	
	public BigDecimal getBuyPrice(String name) {
		BigDecimal buy = getTransactionsForNameAndTypeSorted(name, "kupno").stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal sell = getTransactionsForNameAndTypeSorted(name, "sprzedaż").stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
		Integer buyQuantity = getTransactionsForNameAndTypeSorted(name, "kupno").stream().map(Transaction::getQuantity).mapToInt(i->i).sum();
		Integer sellQuantity = getTransactionsForNameAndTypeSorted(name, "sprzedaż").stream().map(Transaction::getQuantity).mapToInt(i->i).sum();
		
		return buy.add(sell.negate()).divide(new BigDecimal( buyQuantity - sellQuantity));
	}
	
	public Collection<StockParams> getParams(){
		Collection<StockParams> result = new ArrayList<>();
		for(String name: getAllStockNames()) {
			result.add(getParamsForName(name));
		}
		return result;
	}
	
	public StockParams getParamsForName(String name) {
		Collection<Transaction> buyTransactions = getTransactionsForNameAndType(name, "kupno");
		Collection<Transaction> sellTransactions = getTransactionsForNameAndType(name, "sprzedaż");
		
		StockParams r = new StockParams();
		r.setName(name);
		r.setBought(getQuantity(buyTransactions.stream()));
		r.setSold(getQuantity(sellTransactions.stream()));
		r.setQuantity(r.getBought() - r.getSold());
		r.setCost(getCost(sellTransactions.stream()).add(getCost(buyTransactions.stream()).negate()));
		r.setCostNet(getCostNet(sellTransactions.stream()).add(getCostNet(buyTransactions.stream()).negate()));
		if (r.getQuantity().intValue() != 0) {
			BigDecimal buyCost = getCost(buyTransactions.stream());
			BigDecimal sellCost = getCost(sellTransactions.stream());
			BigDecimal priceDiff = BigDecimal.ZERO;
			priceDiff = new BigDecimal(r.getQuantity());
			BigDecimal price = buyCost.add(sellCost.negate()).divide(priceDiff, 2, RoundingMode.HALF_UP);
			r.setRealPrice(price);
		}
		return r;
	
	}
	
	private BigDecimal getCost(Stream<Transaction> transactions) {
		return transactions.map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal getCostNet(Stream<Transaction> transactions) {
		return transactions.map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private Integer getQuantity(Stream<Transaction> transactions) {
		return transactions.map(Transaction::getQuantity).mapToInt(i->i).sum();
	}
	
	public Collection<String> getAllStockNamesOld(){
		return data.getTransactions().values().stream()
				.map(x -> x.getName())
				.distinct()
				.sorted((f1, f2) -> f1.compareTo(f2))
				.collect(Collectors.toList());		
	}
	
	public Collection<String> getAllStockNames(){
		return data.getTransactions().values().stream()
				.map(x -> x.getName())
				.distinct()
				.sorted((f1, f2) -> f1.compareTo(f2))
				.collect(Collectors.toList());
	}
		
}
