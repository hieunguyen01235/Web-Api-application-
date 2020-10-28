package com.root.ImplementService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.root.*;
import com.root.Entity.Repository.*;
import com.root.Security.Jwt.JwtUtils;
import com.root.Entity.*;
import com.root.Service.ApiService;
import com.root.chceckString.Utils;
import com.root.payout.Request.ChangePassRequest;
import com.root.payout.Request.LoginRequest;
import com.root.payout.Request.RegisterRequest;
import com.root.payout.messeger.LoginResponseMessage;
import com.root.payout.messeger.ResponseMessage;
@Service
public class ApiIml implements ApiService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;
	
	/*
	 * all -> list
	 */
		
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}
	
	/*
	 * Create user add role
	 */
	@Override
	public ResponseEntity<?> create(RegisterRequest registerRequest) {
		if (userRepository.existsByUsername(registerRequest.getUserName())) {
			return ResponseEntity.badRequest()
					.body(new ResponseMessage(
							new SQLIntegrityConstraintViolationException("Username is already taken").getMessage(),
							new Date()));
		}
		if (Utils.shortPassword(registerRequest.getPassword())) {
			return ResponseEntity.badRequest()
					.body(new ResponseMessage("Password shorter than 6 characters", new Date()));
		}
		if (registerRequest.getUserName() == null ||  registerRequest.getUserName() == "") {
			return ResponseEntity.badRequest()
					.body(new ResponseMessage("User is nul", new Date()));
		}
		User user = new User();
		Set<Role> role = new HashSet<Role>();
		Role userRole = roleRepository.findByname(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Role is not found"));
		
		System.out.println(registerRequest.getUserName());
		user.setUsername(registerRequest.getUserName());
		
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		role.add(userRole);
		
		user.setRoles(role);

		userRepository.save(user);
		
		return ResponseEntity.ok(new ResponseMessage("User registered successfully", new Date()));
	}
	/*
	 * ------------------------------ login
	 */
	
	@Autowired
    private JwtUtils jwtUtil;
	@Override
	public ResponseEntity<?> login(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		if (loginRequest.getUsername() == null || loginRequest.getUsername() == "") {
			return ResponseEntity.badRequest().body(new ResponseMessage("Username is not empty", new Date()));
		}
		try {
			userRepository.findByUsername(loginRequest.getUsername()).get();
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("User is not found", new Date()));
		}
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateJwtToken(authentication);

			return ResponseEntity.ok(new LoginResponseMessage("success", "Login successfully", jwt, new Date()));

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("User or Password are wrong", new Date()));
		}
	}
	/*
	 * ------------------------------ ChangePass
	 * 
	 * get user from Securitu
	 * 
	 * chet usr null
	 * 
	 * check pass inseart == pass databas
	 * 			check type pass
	 * 			check change or not 
	 * 			check pass new == pass re
	 * 			
	 * 
	 */

	@Override
	public ResponseEntity<?> changePass(ChangePassRequest changePassRequest) {
		// TODO Auto-generated method stub
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(username);
		if (user == null) {
			return ResponseEntity.badRequest()
					.body(new ResponseMessage("User " + username + " is not found", new Date()));
		}
		//check database
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean check = encoder.matches(changePassRequest.getOldPassword(), user.get().getPassword());
		
		if( check) {
			if (Utils.shortPassword(changePassRequest.getNewPassword())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("New password shorter than 6 characters", new Date()));
			}
			if( changePassRequest.getNewPassword().equals(changePassRequest.getOldPassword())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessage("New password is the same as the old one", new Date()));
			}
			if (changePassRequest.getNewPassword().equals(changePassRequest.getReNewPassword())) {
				user.get().setPassword(passwordEncoder.encode(changePassRequest.getNewPassword()));
				userRepository.save(user.get());
				return ResponseEntity
						.ok(new ResponseMessage("Password is changed successfully", new Date()));
			}
			return ResponseEntity.badRequest()
					.body(new ResponseMessage("New password is not matched", new Date()));
		}
		
		
		return ResponseEntity.badRequest().body(new ResponseMessage("Old password is not true", new Date()));
	}
	
	
	
	
}
