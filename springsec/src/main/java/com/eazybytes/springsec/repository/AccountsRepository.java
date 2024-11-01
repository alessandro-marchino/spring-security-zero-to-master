package com.eazybytes.springsec.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.springsec.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long>{
	Optional<Accounts> findByCustomerId(Long customerId);

}
