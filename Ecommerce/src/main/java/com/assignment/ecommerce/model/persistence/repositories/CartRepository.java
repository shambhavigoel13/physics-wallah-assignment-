package com.assignment.ecommerce.model.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.ecommerce.model.persistence.Cart;
import com.assignment.ecommerce.model.persistence.UserEntity;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findByUser(UserEntity user);
}
