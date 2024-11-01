package com.eazybytes.springsec.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Accounts;
import com.eazybytes.springsec.repository.AccountsRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountsRepository accountsRepository;

	@GetMapping("/myAccount")
	public Accounts getAccountDetails(@RequestParam Long id) {
		Optional<Accounts> accounts = accountsRepository.findByCustomerId(id);
		return accounts.orElse(null);
	}
}
