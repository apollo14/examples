package pl.js.gpw.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ValidationIndicators {
	private BigDecimal amount;
	private BigDecimal commission;
	private BigDecimal value;

}
