package com.eazybytes.springsec.controller;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Loans;
import com.eazybytes.springsec.repository.LoanRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoansController {
	private final LoanRepository loanRepository;

	@GetMapping("/myLoans")
	@PostAuthorize("hasRole('USER')")
	public List<Loans> getLoansDetails(@RequestParam Long id) {
		return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
	}
}
