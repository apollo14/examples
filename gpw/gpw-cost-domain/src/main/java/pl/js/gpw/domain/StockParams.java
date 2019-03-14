package pl.js.gpw.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockParams {
	private String name;
	private Integer bought;
	private Integer sold;
	private Integer quantity;
	private BigDecimal cost;
	private BigDecimal costNet;
	private BigDecimal realPrice;
}
