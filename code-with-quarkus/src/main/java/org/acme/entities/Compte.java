package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Compte {
	@Id
	@Column(name="CIN")
	public int CIN;
	@Column(name="PASSWORD")
	public String password;
	@Column(name="ROLE")
	public String role;
	@Column(name="USERNAME")
	public String username;
	@Column(name="MOBILE")
	public int mobile;

	public int getCIN() {
		return CIN;
	}

	public void setCIN(int CIN) {
		this.CIN = CIN;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public Compte(int CIN, String password, String role, String username, int mobile) {
		this.CIN = CIN;
		this.password = password;
		this.role = role;
		this.username = username;
		this.mobile = mobile;
	}

	public Compte(){}
}