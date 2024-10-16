package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String displayLoginPage() {
		return "login.html";
	}

}
