package com.eazybytes.springsec.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import com.eazybytes.springsec.exceptionhandling.CustomAccessDeniedHandler;
import com.eazybytes.springsec.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.eazybytes.springsec.filter.CsrfCookieFilter;

@Configuration
@Profile({ "!prod" })
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		CsrfTokenRequestAttributeHandler csrfTokenRequestHandler = new CsrfTokenRequestAttributeHandler();
		
		return http
			.cors(cc -> cc.configurationSource(request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(Boolean.TRUE);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}))
			.sessionManagement(smc -> smc
					.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
					.sessionConcurrency(scc -> scc
							.maximumSessions(3)
							.maxSessionsPreventsLogin(true)))
			.securityContext(scc -> scc.requireExplicitSave(false))
			.requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
			.authorizeHttpRequests(req -> req
				.requestMatchers("/myAccount").hasRole("USER")
				.requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/myLoans").hasRole("USER")
				.requestMatchers("/myCards").hasRole("USER")
				.requestMatchers("/user").authenticated()
				.requestMatchers("/notices", "/contact", "/register", "/error", "/invalidSession").permitAll())
			.formLogin(Customizer.withDefaults())
			.httpBasic(hbc ->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
			.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()))
			.csrf(csrfConfig -> csrfConfig
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.csrfTokenRequestHandler(csrfTokenRequestHandler)
				.ignoringRequestMatchers("/contact", "/register"))
			.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
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
