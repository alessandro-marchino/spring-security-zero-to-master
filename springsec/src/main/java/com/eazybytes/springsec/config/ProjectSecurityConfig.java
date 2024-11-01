package com.eazybytes.springsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.eazybytes.springsec.exceptionhandling.CustomAccessDeniedHandler;
import com.eazybytes.springsec.exceptionhandling.CustomBasicAuthenticationEntryPoint;

@Configuration
@Profile({ "!prod" })
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.sessionManagement(smc -> smc
					.invalidSessionUrl("/invalidSession")
					.sessionConcurrency(scc -> scc
							.maximumSessions(3)
							.maxSessionsPreventsLogin(true)))
			.requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
			.authorizeHttpRequests(req -> req
				.requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards", "/user").authenticated()
				.requestMatchers("/notices", "/contact", "/register", "/error", "/invalidSession").permitAll())
			.formLogin(Customizer.withDefaults())
			.httpBasic(hbc ->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
			.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()))
			.csrf(csrfConfig -> csrfConfig.disable())
			.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// Attivazione opzionale
//	@Bean
//	CompromisedPasswordChecker compromisedPasswordChecker() {
//		return new HaveIBeenPwnedRestApiPasswordChecker();
//	}
}
