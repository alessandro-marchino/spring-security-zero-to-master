package com.eazybytes.springsec.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.springsec.constants.ApplicationConstants;
import com.eazybytes.springsec.model.Customer;
import com.eazybytes.springsec.model.LoginRequestDTO;
import com.eazybytes.springsec.model.LoginResponseDTO;
import com.eazybytes.springsec.repository.CustomerRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final Environment environment;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
		try {
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			customer.setCreateDt(LocalDate.now());
			Customer savedCustomer = customerRepository.save(customer);
			if(savedCustomer.getCustomerId() != null) {
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered");
			}
			return ResponseEntity.badRequest().body("User registration failed");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("An exception occurred: " + e.getMessage());
		}
	}
	
	@GetMapping("/user")
	private Customer getUserDetailsAfterLogin(Authentication authentication) {
		return customerRepository.findByEmail(authentication.getName())
				.orElse(null);
	}

	@PostMapping("/apiLogin")
	public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequest) {
		String jwt = null;
		Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
		Authentication authenticationResponse = authenticationManager.authenticate(authentication);
		if(authenticationResponse != null && authenticationResponse.isAuthenticated()) {
			String jwtSecret = environment.getProperty(ApplicationConstants.JWT_SECRET, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
			SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
			jwt = Jwts.builder()
				.issuer("Eazy Bank")
				.subject("JWT Token")
				.claim("username", authenticationResponse.getName())
				.claim("authorities", authenticationResponse.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
				.issuedAt(new Date())
				// 8 hours
				.expiration(new Date(new Date().getTime() + 28800000))
				.signWith(secretKey)
				.compact();
		}
		return ResponseEntity.status(HttpStatus.OK)
				.header(ApplicationConstants.JWT_HEADER, jwt)
				.body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
	}
}
