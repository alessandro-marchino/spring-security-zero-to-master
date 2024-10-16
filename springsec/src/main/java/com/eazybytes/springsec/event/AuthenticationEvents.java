package com.eazybytes.springsec.event;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationEvents {

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent event) {
		log.info("Login successful for the user: {}", event.getAuthentication().getName());
	}

	@EventListener
	public void onFailure(AbstractAuthenticationFailureEvent event) {
		log.error("Login failed for the user: {} due to: {}", event.getAuthentication().getName(), event.getException().getMessage());
	}
}