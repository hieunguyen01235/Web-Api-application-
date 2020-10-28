package com.root.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.root.payout.Request.LoginRequest;




@RestController
public class Controller {

	@GetMapping("/test")
	public String tes() {
//		LoginRequest check1 ;''
		LoginRequest re = new LoginRequest();

//		check1.setUsername("user name n");
		re.setUsername("sdsd");
//		
		return re.getUsername();
//		return null;
	}
	

}
