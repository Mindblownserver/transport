package org.acme.entities;

public class  AuthRequest {
	public String username;
	public String password;

	public AuthRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public AuthRequest() {}
}