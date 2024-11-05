package com.ambev.order.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ambev.order.dto.CartDTO;
import com.ambev.order.dto.OrderDTO;
import com.ambev.order.service.OrderService;

@RestController
public class OrderResourceImpl implements OrderResource {

	@Autowired
	OrderService service;
	
	@Override
	public ResponseEntity<OrderDTO> postOrder(OrderDTO order) {
		return new ResponseEntity<>(this.service.saveOrder(order), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		return new ResponseEntity<>(this.service.getall(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<OrderDTO> getOrderById(UUID id) {
		return new ResponseEntity<>(this.service.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CartDTO> getAllOrdersByStatus(String status) {
		return new ResponseEntity<>(this.service.getAllOrdersByStatus(status), HttpStatus.OK);
	}
}
