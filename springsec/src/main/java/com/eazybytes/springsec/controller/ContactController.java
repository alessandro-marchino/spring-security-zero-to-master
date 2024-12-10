package com.eazybytes.springsec.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
//	@PreFilter("filterObject.contactName != 'TEST'")
//	@PostFilter("filterObject.contactName != 'TEST'")
	public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
		if(contacts.isEmpty()) {
			return List.of();
		}
		List<Contact> returnContacts = new ArrayList<>();
		Contact contact = contacts.getFirst();
		contact.setContactId(getServiceReqNumber());
		contact.setCreateDt(LocalDate.now());
		returnContacts.add(contactRepository.save(contact));
		return returnContacts;
	}
	private String getServiceReqNumber() {
		Random random = new Random();
		int ranNum = random.nextInt(999999999 - 9999) + 9999;
		return "SR" + ranNum;
	}
}
