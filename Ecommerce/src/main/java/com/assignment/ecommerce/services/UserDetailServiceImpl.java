package com.assignment.ecommerce.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.ecommerce.model.persistence.UserEntity;
import com.assignment.ecommerce.model.persistence.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String contactNumber) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByContactNumber(contactNumber);
		if (userEntity == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}
		return new User(userEntity.getContactNumber(), userEntity.getPassword(), Collections.emptyList());
	}

}
