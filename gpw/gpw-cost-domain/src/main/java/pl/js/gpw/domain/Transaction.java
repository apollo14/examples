package pl.js.gpw.domain;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"date", "number", "name", "type", "quantity", "price", "amount", "commission", "value"})
public class Transaction {
	public static final String TRANSACTION_DATE_FORMAT = "dd-MM-yyyy HH-mm-ss"; 
	
	@JsonProperty("Data transakcji")
	private Date date;
	@JsonProperty("Numer zlecenia")
	private Integer number;
	@JsonProperty("Papier")
	private String name;
	@JsonProperty("Kierunek")
	private String type;
	@JsonProperty("Ilość")
	private Integer quantity;
	@JsonProperty("Kurs")
	private BigDecimal price;
	@JsonProperty("Wartość")
	private BigDecimal amount;
	@JsonProperty("Prowizja")
	private BigDecimal commission;
	@JsonProperty("Wartość z prowizją")
	private BigDecimal value;
		
	@JsonCreator
	public static Transaction factory(			
			@JsonProperty("Data transakcji") String date,
			@JsonProperty("Numer zlecenia") Integer number,
			@JsonProperty("Papier") String name,
			@JsonProperty("Kierunek") String type,
			@JsonProperty("Ilość") Integer quantity,
			@JsonProperty("Kurs") String price,
			@JsonProperty("Wartość") String amount,
			@JsonProperty("Prowizja") String commission,
			@JsonProperty("Wartość z prowizją") String value
			) {
		Date transactionDate;
		try {
			transactionDate = new SimpleDateFormat(TRANSACTION_DATE_FORMAT).parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(String.format("Transaction date parse error: %s" , date));
		}
		
		return new Transaction(
				transactionDate, 
				number, 
				name, 
				type, 
				quantity, 
				new BigDecimal(price.replace(",", ".")), 
				new BigDecimal(amount.replace(",", ".")), 
				new BigDecimal(commission.replace(",", ".")), 
				new BigDecimal(value.replace(",", ".")));
	
	}
	
	public Transaction merge(Transaction next) {
		Transaction merged = new Transaction(date, number, name, type, quantity, price, amount, commission, value); 
		if (number.equals(next.number)) {
			merged.quantity =  quantity+next.quantity;
			merged.amount = amount.add(next.amount);
			merged.commission= commission.add(next.commission);
			merged.value= value.add(next.value);
		}
		return merged;
	}
}
