package com.assignment.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.ecommerce.model.persistence.Cart;
import com.assignment.ecommerce.model.persistence.UserEntity;
import com.assignment.ecommerce.model.persistence.repositories.CartRepository;
import com.assignment.ecommerce.model.persistence.repositories.UserRepository;
import com.assignment.ecommerce.model.requests.CreateUserRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@GetMapping("/id/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserEntity> findByUserName(@PathVariable String username) {
		UserEntity user = userRepository.findByContactNumber(username);
		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}

	@PostMapping("/create")
	public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserRequest createUserRequest) {

		UserEntity user = new UserEntity();

		if (createUserRequest.getContactNumber() == null || createUserRequest.getContactNumber().length() != 10) {
			return ResponseEntity.badRequest().build();
		}
		try {
			Long.parseLong(createUserRequest.getContactNumber());
		} catch (NumberFormatException exc) {
			return ResponseEntity.badRequest().build();
		}
		user.setContactNumber(createUserRequest.getContactNumber());

		if (createUserRequest.getPassword().length() < 7 || createUserRequest.getPassword() == null
				|| !createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
			return ResponseEntity.badRequest().build();
		}
		user.setPassword(bcryptEncoder.encode(createUserRequest.getPassword()));

		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}

}
