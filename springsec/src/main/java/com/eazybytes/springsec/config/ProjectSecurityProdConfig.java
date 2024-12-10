package com.eazybytes.springsec.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import com.eazybytes.springsec.exceptionhandling.CustomAccessDeniedHandler;
import com.eazybytes.springsec.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.eazybytes.springsec.filter.AuthoritiesLoggerAfterFilter;
import com.eazybytes.springsec.filter.AuthoritiesLoggingAtFilter;
import com.eazybytes.springsec.filter.CsrfCookieFilter;
import com.eazybytes.springsec.filter.JWTTokenGeneratorFilter;
import com.eazybytes.springsec.filter.JWTTokenValidatorFilter;
import com.eazybytes.springsec.filter.RequestValidationBeforeFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Profile({ "prod" })
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class ProjectSecurityProdConfig {
	private final JWTTokenValidatorFilter jwtTokenValidatorFilter;

	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		AuthenticationProvider authenticationProvider = new EazyBankProdUsernamePwdAuthenticationProvider(userDetailsService, passwordEncoder);
		ProviderManager providerManager = new ProviderManager(authenticationProvider);
		providerManager.setEraseCredentialsAfterAuthentication(false);
		return providerManager;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler csrfTokenRequestHandler = new CsrfTokenRequestAttributeHandler();
		return http
			.cors(cc -> cc.configurationSource(request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("https://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(Boolean.TRUE);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setExposedHeaders(List.of("Authorization"));
				config.setMaxAge(3600L);
				return config;
			}))
			.sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.requiresChannel(rcc -> rcc.anyRequest().requiresSecure())
			.authorizeHttpRequests(req -> req
				.requestMatchers("/myAccount").hasRole("USER")
				.requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/myLoans").authenticated()
				.requestMatchers("/myCards").hasRole("USER")
				.requestMatchers("/user").authenticated()
				.requestMatchers("/notices", "/contact", "/register", "/error", "/apiLogin").permitAll())
			.formLogin(Customizer.withDefaults())
			.httpBasic(hbc ->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
			.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()))
			.csrf(csrfConfig -> csrfConfig
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.csrfTokenRequestHandler(csrfTokenRequestHandler)
				.ignoringRequestMatchers("/contact", "/register", "/apiLogin"))
			.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
			.addFilterAfter(new AuthoritiesLoggerAfterFilter(), BasicAuthenticationFilter.class)
			.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
			.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
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
