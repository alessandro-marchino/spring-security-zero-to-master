package com.eazybytes.springsec.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.AccountTransactions;
import com.eazybytes.springsec.repository.AccountTransactionsRepository;
import com.eazybytes.springsec.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BalanceController {

	private final AccountTransactionsRepository accountTransactionsRepository;
	private final CustomerRepository customerRepository;

	@GetMapping("/myBalance")
	public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
		return customerRepository.findByEmail(email)
			.map(c -> accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(c.getCustomerId()))
			.orElse(null);
	}
}
