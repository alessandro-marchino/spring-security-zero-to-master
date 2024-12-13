package com.eazybytes.springsecoauth2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "eazybytes")
@Component
@Data
public class EazybytesConfiguration {
  private Oauth2Configuration github;
  private Oauth2Configuration facebook;

  @Data
  public static class Oauth2Configuration {
    private boolean enabled = false;
    private String clientId;
    private String clientSecret;
  }
}
