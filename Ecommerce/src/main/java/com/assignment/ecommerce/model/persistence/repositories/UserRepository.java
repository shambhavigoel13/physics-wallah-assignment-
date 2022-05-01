package com.assignment.ecommerce.model.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.ecommerce.model.persistence.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByContactNumber(String contactNumber);
}
