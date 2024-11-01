package com.eazybytes.springsec.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Loans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanNumber;
	private Long customerId;
	@Temporal(TemporalType.DATE)
	private LocalDate startDt;
	private String loanType;
	private Integer totalLoan;
	private Integer amountPaid;
	private Integer outstandingAmount;
	@Temporal(TemporalType.DATE)
	private LocalDate createDt;
}
