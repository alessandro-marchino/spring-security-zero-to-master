package com.eazybytes.springsec.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Notice;
import com.eazybytes.springsec.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticesController {

	private final NoticeRepository noticeRepository;

	@GetMapping("/notices")
	public ResponseEntity<List<Notice>> getNotices() {
		List<Notice> notices = noticeRepository.findAllActiveNotices();
		if(notices.isEmpty()) {
			return null;
		}
		return ResponseEntity.ok()
			.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
			.body(notices);
	}
}
