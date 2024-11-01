package com.eazybytes.springsec.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
			customer.setCreateDt(LocalDate.now());
			Customer savedCustomer = customerRepository.save(customer);
			if(savedCustomer.getCustomerId() != null) {
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered");
			}
			return ResponseEntity.badRequest().body("User registration failed");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("An exception occurred: " + e.getMessage());
		}
	}
	
	@GetMapping("/user")
	private Customer getUserDetailsAfterLogin(Authentication authentication) {
		return customerRepository.findByEmail(authentication.getName())
				.orElse(null);
	}
}
