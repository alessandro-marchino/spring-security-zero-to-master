package com.eazybytes.eazyschool.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.eazybytes.eazyschool.model.Contact;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ApplicationScope
public class ContactService {

	private int counter = 0;

	public ContactService() {
		log.info("Contact Service Bean initialized");
	}

	/**
	 * Save Contact Details into DB
	 * 
	 * @param contact
	 * @return boolean
	 */
	public boolean saveMessageDetails(Contact contact) {
		boolean isSaved = true;
		// TODO - Need to persist the data into the DB table
		log.info(contact.toString());
		return isSaved;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
