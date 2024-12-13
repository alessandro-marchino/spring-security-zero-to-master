package com.eazybytes.springsec.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Customer;
import com.eazybytes.springsec.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final CustomerRepository customerRepository;

	@GetMapping("/user")
	private Customer getUserDetailsAfterLogin(Authentication authentication) {
		return customerRepository.findByEmail(authentication.getName())
				.orElse(null);
	}

}
