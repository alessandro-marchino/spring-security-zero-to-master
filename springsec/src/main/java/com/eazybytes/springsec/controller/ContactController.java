package com.eazybytes.springsec.controller;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Contact;
import com.eazybytes.springsec.repository.ContactRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContactController {

	private final ContactRepository contactRepository;
	@PostMapping("/contact")
	public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
		contact.setContactId(getServiceReqNumber());
		contact.setCreateDt(LocalDate.now());
		return contactRepository.save(contact);
	}
	private String getServiceReqNumber() {
		Random random = new Random();
		int ranNum = random.nextInt(999999999 - 9999) + 9999;
		return "SR" + ranNum;
	}
}
