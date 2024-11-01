package com.eazybytes.springsec.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.model.Cards;
import com.eazybytes.springsec.repository.CardsRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CardsController {
	private final CardsRepository cardsRepository;

	@GetMapping("/myCards")
	public List<Cards> getCardsDetails(@RequestParam Long id) {
		return cardsRepository.findByCustomerId(id);
	}
}
