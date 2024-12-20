package com.eazybytes.springsec.exceptionhandling;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setHeader("eazybank-error-reason", "Authentication failed");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
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
			HttpStatus.UNAUTHORIZED.value(),
			HttpStatus.UNAUTHORIZED.getReasonPhrase(),
			authException != null && authException.getMessage() != null ? authException.getMessage() : "Unauthorized",
			request.getRequestURI());
		response.getWriter().write(jsonResponse);
		
	}

}
