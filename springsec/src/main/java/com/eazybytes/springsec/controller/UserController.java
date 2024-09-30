package com.eazybytes.springsec.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Customer;
import com.eazybytes.springsec.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
		try {
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			Customer savedCustomer = customerRepository.save(customer);
			if(savedCustomer.getId() != null) {
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered");
			}
			return ResponseEntity.badRequest().body("User registration failed");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("An exception occurred: " + e.getMessage());
		}
	}
}
