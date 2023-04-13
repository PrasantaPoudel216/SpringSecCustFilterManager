package com.CustFiltermanager.code.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.CustFiltermanager.code.token.MyCustAuthToken;


@Component
public class MyCustomFilter  implements Filter {
	
	public static Logger logger=LoggerFactory.getLogger(MyCustomFilter.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(logger.isDebugEnabled()) {
			logger.debug(MyCustomFilter.class+"dofilter");
		}
		///taking request
		HttpServletRequest req= (HttpServletRequest) request;
		String authObj=req.getHeader("secret-key");
		try {
		//creating authenticate object
		MyCustAuthToken authenticate=new MyCustAuthToken(authObj,null);
		///calling authentication manager with authenticate object & receiving authencticate obj returned 
		
		Authentication authenticationobj=authenticationManager.authenticate(authenticate);
		
		if(authenticationobj.isAuthenticated())
		{
			//creating new security context and saving authenctication obj in it
		SecurityContext securityContext=SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authenticationobj);
		SecurityContextHolder.setContext(securityContext);
		}
		
	   
		}catch(Exception e)
		{
			e.printStackTrace();
			if(logger.isDebugEnabled()) {
				logger.debug(MyCustomFilter.class+"dofilter"+e.getLocalizedMessage());
			}
	
		}
		
		chain.doFilter(request, response);
	}
	
	

}
