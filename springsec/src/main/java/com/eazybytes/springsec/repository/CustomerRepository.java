package com.eazybytes.springsec.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.springsec.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);
}
