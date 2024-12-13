package com.eazybytes.springsecoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .authorizeHttpRequests(requests -> requests
        .requestMatchers("/secure").authenticated()
        .anyRequest().permitAll())
      .formLogin(Customizer.withDefaults())
      .oauth2Login(Customizer.withDefaults())
      .build();
  }

}
