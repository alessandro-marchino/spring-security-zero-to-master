package com.eazybytes.springsec.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.springsec.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String>{

}
