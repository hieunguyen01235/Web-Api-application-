package com.root.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.root.Entity.User;
import com.root.payout.Request.ChangePassRequest;
import com.root.payout.Request.LoginRequest;
import com.root.payout.Request.RegisterRequest;
import com.root.respon.ChangePassRespon;



public interface ApiService {
	
	List<User> findAllUser();

	ResponseEntity<?> create(RegisterRequest registerRequest);
	
	ResponseEntity<?> login(LoginRequest loginRequest);
	
	ResponseEntity<?> changePass(ChangePassRequest changePassRequest);
}
