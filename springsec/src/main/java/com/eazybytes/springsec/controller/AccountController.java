package com.eazybytes.springsec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Accounts;
import com.eazybytes.springsec.repository.AccountsRepository;
import com.eazybytes.springsec.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountsRepository accountsRepository;
	private final CustomerRepository customerRepository;

	@GetMapping("/myAccount")
	public Accounts getAccountDetails(@RequestParam String email) {
		return customerRepository.findByEmail(email)
			.flatMap(c -> accountsRepository.findByCustomerId(c.getCustomerId()))
			.orElse(null);
	}
}
