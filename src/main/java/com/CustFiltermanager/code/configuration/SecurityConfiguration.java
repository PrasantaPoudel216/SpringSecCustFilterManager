package com.CustFiltermanager.code.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.CustFiltermanager.code.filter.MyCustomFilter;
import com.CustFiltermanager.code.provider.CustomAuthProvider;
import com.CustFiltermanager.code.token.MyCustAuthToken;


@Order(value = 99)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthProvider custAuthProvider;

	@Autowired
	MyCustomFilter myCustomFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(custAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterAt(myCustomFilter, BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().authenticated();
	}

}
