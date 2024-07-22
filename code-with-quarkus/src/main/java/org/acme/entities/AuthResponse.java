package org.acme.entities;

public class AuthResponse {
	public int CIN;
	public String username;
	public int mobile;
	public String accessToken;
	public String refreshToken;
	public String role;

	public AuthResponse(int CIN, String username, int mobile, String accessToken, String refreshToken, String role) {
		this.CIN = CIN;
		this.username = username;
		this.mobile = mobile;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.role = role;
	}
	public AuthResponse(){}
}