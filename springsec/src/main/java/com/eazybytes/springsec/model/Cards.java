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
public class Cards {

	@Id
	private Long cardId;
	private String cardNumber;
	private Long customerId;
	private String cardType;
	private Integer totalLimit;
	private Integer amountUsed;
	private Integer availableAmount;
	@Temporal(TemporalType.DATE)
	private LocalDate createDt;
}
