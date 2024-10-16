package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;

	@GetMapping("/contact")
	public String displayContactPage(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact.html";
	}

	/*
	 * @RequestMapping(value = "/saveMsg",method = POST) public ModelAndView
	 * saveMessage(@RequestParam String name, @RequestParam String mobileNum,
	 * 
	 * @RequestParam String email, @RequestParam String subject, @RequestParam
	 * String message) { log.info("Name : " + name); log.info("Mobile Number : " +
	 * mobileNum); log.info("Email Address : " + email); log.info("Subject : " +
	 * subject); log.info("Message : " + message); return new
	 * ModelAndView("redirect:/contact"); }
	 */

	@PostMapping(value = "/saveMsg")
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
		if (errors.hasErrors()) {
			log.error("Contact form validation failed due to : {}", errors.toString());
			return "contact.html";
		}
		contactService.saveMessageDetails(contact);
		contactService.setCounter(contactService.getCounter() + 1);
		log.info("Number of times the Contact form is submitted : {}", contactService.getCounter());
		return "redirect:/contact";
	}

}