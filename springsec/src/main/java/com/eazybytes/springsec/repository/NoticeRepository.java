package com.eazybytes.springsec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.springsec.model.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long>{

	@Query("FROM Notice n WHERE CURDATE() BETWEEN n.noticBegDt AND n.noticEndDt")
	List<Notice> findAllActiveNotices();

}
