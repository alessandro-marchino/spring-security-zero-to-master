package com.eazybytes.springsec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.springsec.model.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {

//	@PreAuthorize("hasRole('USER')")
	List<Loans> findByCustomerIdOrderByStartDtDesc(Long customerId);

}
