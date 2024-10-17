package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String displayLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
		String errorMessage = null;
		if(error != null) {
			errorMessage = "Username or Password is incorrect!";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "login.html";
	}

}
