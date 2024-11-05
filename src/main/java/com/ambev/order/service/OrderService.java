package com.ambev.order.service;

import java.util.List;
import java.util.UUID;

import com.ambev.order.dto.CartDTO;
import com.ambev.order.dto.OrderDTO;

public interface OrderService {

	OrderDTO saveOrder(OrderDTO order);

	List<OrderDTO> getall();

	OrderDTO getById(UUID id);

	CartDTO getAllOrdersByStatus(String status);

}
