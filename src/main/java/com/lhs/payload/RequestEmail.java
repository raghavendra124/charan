package com.lhs.payload;

public class RequestEmail {
	
	
	public RequestEmail() {
		super();
	}

	public RequestEmail(String email) {
		super();
		this.email = email;
	}

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
