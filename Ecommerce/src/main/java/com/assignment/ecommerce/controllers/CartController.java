package com.assignment.ecommerce.controllers;

import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.ecommerce.model.persistence.Cart;
import com.assignment.ecommerce.model.persistence.Item;
import com.assignment.ecommerce.model.persistence.UserEntity;
import com.assignment.ecommerce.model.persistence.repositories.CartRepository;
import com.assignment.ecommerce.model.persistence.repositories.ItemRepository;
import com.assignment.ecommerce.model.persistence.repositories.UserRepository;
import com.assignment.ecommerce.model.requests.ModifyCartRequest;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostMapping("/addToCart")
	public ResponseEntity<Cart> addTocart(@RequestBody ModifyCartRequest request) {
		if (request.getItemId() <= 0 || request.getQuantity() <= 0 || request.getContactNumber() == null
				|| request.getContactNumber().length() != 10) {
			return ResponseEntity.badRequest().build();
		}

		UserEntity user = userRepository.findByContactNumber(request.getContactNumber());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Optional<Item> item = itemRepository.findById(request.getItemId());
		if (!item.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Cart cart = user.getCart();
		IntStream.range(0, request.getQuantity()).forEach(i -> cart.addItem(item.get()));
		cartRepository.save(cart);
		return ResponseEntity.ok(cart);
	}

	@PostMapping("/removeFromCart")
	public ResponseEntity<Cart> removeFromcart(@RequestBody ModifyCartRequest request) {
		if (request.getItemId() <= 0 || request.getQuantity() <= 0 || request.getContactNumber() == null
				|| request.getContactNumber().length() != 10) {
			return ResponseEntity.badRequest().build();
		}
		try {
			Long.parseLong(request.getContactNumber());
		} catch (NumberFormatException exc) {
			return ResponseEntity.badRequest().build();
		}
		UserEntity user = userRepository.findByContactNumber(request.getContactNumber());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Optional<Item> item = itemRepository.findById(request.getItemId());
		if (!item.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Cart cart = user.getCart();
		IntStream.range(0, request.getQuantity()).forEach(i -> cart.removeItem(item.get()));
		cartRepository.save(cart);
		return ResponseEntity.ok(cart);
	}

}
