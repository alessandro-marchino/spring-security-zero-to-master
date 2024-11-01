package com.eazybytes.springsec.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.AccountTransactions;
import com.eazybytes.springsec.repository.AccountTransactionsRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BalanceController {

	private final AccountTransactionsRepository accountTransactionsRepository;

	@GetMapping("/myBalance")
	public List<AccountTransactions> getBalanceDetails(@RequestParam Long id) {
		return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
	}
}
