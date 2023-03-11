package paf.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.order.model.Order;
import paf.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	//Method to insert a new order record
	public String insertOrder(Order order) {

		//Generate a random orderId and set to order object
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);

		//Insert the order record and return orderId
		orderRepo.insertOrder(order);
		return orderId;

	}

}