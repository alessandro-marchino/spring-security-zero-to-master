package com.eazybytes.springsec.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contact_message")
public class Contact {
	
	@Id
	private String contactId;
	private String contactName;
	private String contactEmail;
	private String subject;
	private String message;
	@Temporal(TemporalType.DATE)
	private LocalDate createDt;

}
