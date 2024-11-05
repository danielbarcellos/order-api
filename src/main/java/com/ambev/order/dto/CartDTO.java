package com.ambev.order.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	@Setter
	@Getter
	private List<OrderDTO> orders;
	
	public BigDecimal getAmount() {
		return this.orders == null || this.orders.isEmpty() ? BigDecimal.ZERO : this.orders.stream().map(v -> v.getValue()).reduce((a,b) -> a.add(b)).orElse(BigDecimal.ZERO);
	}
}
