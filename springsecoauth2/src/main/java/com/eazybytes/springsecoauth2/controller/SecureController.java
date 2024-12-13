package com.eazybytes.springsecoauth2.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SecureController {

  @GetMapping("/secure")
  public String securePage(Authentication authentication) {
    if(authentication instanceof UsernamePasswordAuthenticationToken token) {
      log.warn("Token: {}", token);
    } else if (authentication instanceof OAuth2AuthenticationToken token) {
      log.warn("Token: {}", token);
    }
    return "secure.html";
  }
}
