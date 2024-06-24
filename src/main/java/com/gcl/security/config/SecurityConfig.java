package com.gcl.security.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	// private final JwtAuthenticationConverter jwtAuthenticationConverter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(http -> {
			// http.requestMatchers("/create-user").permitAll();

			http.anyRequest().authenticated();
		}).oauth2ResourceServer(configure -> configure.jwt().jwtAuthenticationConverter(jwtAuthConverter()))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
	}


	Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());

		return converter;
	}
}

@SuppressWarnings("unchecked")
class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		if (jwt.getClaims() == null) {
			return List.of();
		}

		final Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");

		return realmAccess.get("roles").stream().map(roleName -> "ROLE_" + roleName.toUpperCase()).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
}
