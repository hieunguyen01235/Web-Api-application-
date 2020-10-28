package com.root.payout.messeger;

import java.util.Date;

public class LoginResponseMessage {
	private String status;
	private String message;
	private String type;
	private String token;
	private Date timetamp;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getTimetamp() {
		return timetamp;
	}
	public void setTimetamp(Date timetamp) {
		this.timetamp = timetamp;
	}
	public LoginResponseMessage(String status, String message, String token, Date timetamp) {
		super();
		this.status = status;
		this.message = message;
		this.type = "Bearer ";
		this.token = token;
		this.timetamp = timetamp;
	}
	
}
