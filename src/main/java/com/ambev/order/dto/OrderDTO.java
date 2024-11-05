package com.ambev.order.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private UUID orderId;
	
	private String description;
	
	private String status;
	
	private BigDecimal value;
}
