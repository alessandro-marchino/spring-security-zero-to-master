package com.eazybytes.springsec.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountTransactions {

	@Id
	private String transactionId;
	private Long accountNumber;
	private Long customerId;
	@Temporal(TemporalType.DATE)
	private LocalDate transactionDt;
	private String transactionSummary;
	private String transactionType;
	private Integer transactionAmt;
	private Integer closingBalance;
	@Temporal(TemporalType.DATE)
	private LocalDate createDt;
}
