package com.ambev.order.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ambev.order.dto.CartDTO;
import com.ambev.order.dto.OrderDTO;


public interface OrderResource {

	public static final String VERSION = "v1";
	
	@PostMapping("/order")
	ResponseEntity<OrderDTO> postOrder(@RequestBody OrderDTO order);
	
	@GetMapping("/orders")
	ResponseEntity<List<OrderDTO>> getAllOrders();
	
	@GetMapping("/orders/search")
	ResponseEntity<CartDTO> getAllOrdersByStatus(@RequestParam("status") String status);
	
	@GetMapping("/orders/{id}")
	ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") UUID id);
}
