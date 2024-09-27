package com.eazybytes.springsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests(req -> req
				.requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
				.requestMatchers("/notices", "/contact", "/error").permitAll())
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults())
			.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
		UserDetails admin = User.withUsername("admin").password("{noop}54321").authorities("admin").build();
		return new InMemoryUserDetailsManager(user, admin);
	}
}
