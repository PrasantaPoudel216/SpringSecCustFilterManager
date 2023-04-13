package com.CustFiltermanager.code.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class BasicController {
	
	@GetMapping("/getmessage")
	public String getMessage()
	{
		return "security passed";
	}
	

}
