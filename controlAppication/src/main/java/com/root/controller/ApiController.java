package com.root.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.root.Service.ApiService;
import com.root.payout.Request.ChangePassRequest;
import com.root.payout.Request.LoginRequest;
import com.root.payout.Request.RegisterRequest;



@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/api/user")
public class ApiController {

	@Autowired

	private ApiService apiService;

	/*
	 * Test
	 */
	@GetMapping("/he")
	public String he() {
		return "helo";
	}
	
	/*=======================================================
	 * Login user and token
	 * ======================================================
	 */
	@PostMapping("/login")
	public ResponseEntity<?> register(@RequestBody  LoginRequest loginRequest)
			throws SQLIntegrityConstraintViolationException {
//		System.out.println("chaty qua hhhhshdhsdhsdkhsdkjsh1");
		return apiService.login(loginRequest);
	}
	
	/*====================================================
	 * Register user
	 * ===================================================
	 */
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest)
			throws SQLIntegrityConstraintViolationException {

		return apiService.create(registerRequest);
	}
	
	
	
	/*====================================================
	 * Change pass 
	 * ===================================================
	 */
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/changepass")
	public ResponseEntity<?> change(@RequestBody ChangePassRequest changePassRequest)
			throws SQLIntegrityConstraintViolationException {

		return apiService.changePass(changePassRequest);
	}
	
	@PreAuthorize("hasRole('USER')")
    @GetMapping("/random")
    public String randomStuff(){
        return "JWT Hợp lệ mới có thể thấy được message này";
    }
}
