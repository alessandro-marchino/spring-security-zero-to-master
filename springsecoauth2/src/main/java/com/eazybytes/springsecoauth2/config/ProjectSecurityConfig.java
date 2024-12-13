package com.eazybytes.springsecoauth2.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {
  private final EazybytesConfiguration eazybytesConfiguration;

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

  @Bean
  ClientRegistrationRepository clientRegistrationRepository() {
    List<ClientRegistration> clientRegistrations = new ArrayList<>();
    if(eazybytesConfiguration.getGithub().isEnabled()) {
      clientRegistrations.add(githubClientRegistration());
    }
    if(eazybytesConfiguration.getFacebook().isEnabled()) {
      clientRegistrations.add(facebookClientRegistration());
    }
    return new InMemoryClientRegistrationRepository(clientRegistrations);
  }

  private ClientRegistration githubClientRegistration() {
    return CommonOAuth2Provider.GITHUB
      .getBuilder("github")
      .clientId(eazybytesConfiguration.getGithub().getClientId())
      .clientSecret(eazybytesConfiguration.getGithub().getClientSecret())
      .build();
  }

  private ClientRegistration facebookClientRegistration() {
    return CommonOAuth2Provider.FACEBOOK
      .getBuilder("facebook")
      .clientId("")
      .clientSecret("")
      .build();
  }
}
