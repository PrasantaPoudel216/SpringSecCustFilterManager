package com.CustFiltermanager.code.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.CustFiltermanager.code.token.MyCustAuthToken;

@Component
public class CustomAuthProvider implements AuthenticationProvider{
	
	
	
	@Value("${secret-key}")
	String secretkey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName=authentication.getName();
		
		if(secretkey.equals(userName)) {
		return new MyCustAuthToken(null, null,null);
		}
		return (Authentication) new BadCredentialsException("not matched given credentials");
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return  MyCustAuthToken.class.equals(authentication);
	}

}
