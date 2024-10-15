package com.eazybytes.springsec.exceptionhandling;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setHeader("eazybank-denied-reason", "Authentication failed");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		String jsonResponse = String.format("""
			{
				"timestamp": "%s",
				"status": %d,
				"error": "%s",
				"message": "%s",
				"path": "%s"
			}
			""",
			LocalDateTime.now(),
			HttpStatus.FORBIDDEN.value(),
			HttpStatus.FORBIDDEN.getReasonPhrase(),
			accessDeniedException != null && accessDeniedException.getMessage() != null ? accessDeniedException.getMessage() : "Forbidden",
			request.getRequestURI());
		response.getWriter().write(jsonResponse);
		
	}

}
