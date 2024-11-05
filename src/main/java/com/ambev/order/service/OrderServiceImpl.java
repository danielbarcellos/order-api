package com.ambev.order.service;

import static com.ambev.order.exception.ApiExceptionUtils.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ambev.order.dto.CartDTO;
import com.ambev.order.dto.OrderDTO;

@Service
public class OrderServiceImpl implements OrderService {

	private final Map<UUID, OrderDTO> DB = Collections.synchronizedMap(new HashMap<>());

	@Override
	public OrderDTO saveOrder(OrderDTO order) {
		UUID uuid = UUID.randomUUID();
		order.setOrderId(uuid);
		order.setStatus("Ativo");

		return DB.put(uuid, order);
	}

	@Override
	public CartDTO getAllOrdersByStatus(String status) {
		return CartDTO.builder()
				.orders(this.getall().stream().filter(o -> status.equalsIgnoreCase(o.getStatus())).collect(Collectors.toList())).build();
	}

	@Override
	public List<OrderDTO> getall() {
		return DB.values().stream(). collect(Collectors.toList());
	}

	@Override
	public OrderDTO getById(UUID id) {
		assertTrue(id != null, HttpStatus.BAD_REQUEST, "id mandatory");
		assertTrue(DB.containsKey(id), HttpStatus.NOT_FOUND, "order not found");

		return DB.get(id);
	}

}
