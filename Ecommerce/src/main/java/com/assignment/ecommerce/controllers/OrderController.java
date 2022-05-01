package com.assignment.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.ecommerce.model.persistence.UserEntity;
import com.assignment.ecommerce.model.persistence.UserOrder;
import com.assignment.ecommerce.model.persistence.repositories.OrderRepository;
import com.assignment.ecommerce.model.persistence.repositories.UserRepository;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/submit/{contactNumber}")
	public ResponseEntity<UserOrder> submit(@PathVariable String contactNumber) {
		UserEntity user = userRepository.findByContactNumber(contactNumber);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		UserOrder order = UserOrder.createFromCart(user.getCart());
		orderRepository.save(order);
		return ResponseEntity.ok(order);
	}

}
