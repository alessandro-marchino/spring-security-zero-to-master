package com.eazybytes.springsec.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter;

public class KeycloakOpaqueRoleConverter implements OpaqueTokenAuthenticationConverter {

	@Override
	public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
		String username = authenticatedPrincipal.getAttribute("preferred_username");
		Map<String, Object> realmAccess = authenticatedPrincipal.getAttribute("realm_access");
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> roles = ((List<String>)realmAccess.get("roles"))
			.stream()
			.map(roleName -> "ROLE_" + roleName)
			.map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role))
			.toList();
		return new UsernamePasswordAuthenticationToken(username, null, roles);
	}

}
