package com.cts.training.netflixzuulapigatewayserver.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	
	@GetMapping("login")
	public String login(Authentication auth) {
		log.info(auth.getName());
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			log.info("Authorities: {}",authority.getAuthority());
			if (authority.getAuthority().equals("ROLE_USER")) {
				return "user";
			}
			if (authority.getAuthority().equals("ROLE_ADMIN")) {
				return "admin";
			}
		}
		return "unauthorized";
	}
}
