package com.eazybytes.springsec.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "notice_details")
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noticeId;
	private String noticeSummary;
	private String noticeDetails;
	@Temporal(TemporalType.DATE)
	private LocalDate noticBegDt;
	@Temporal(TemporalType.DATE)
	private LocalDate noticEndDt;
	@Temporal(TemporalType.DATE)
	private LocalDate createDt;
	@Temporal(TemporalType.DATE)
	private LocalDate updateDt;

}
