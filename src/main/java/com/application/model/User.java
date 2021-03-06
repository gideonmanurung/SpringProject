package com.application.model;

import javax.persistence.*;

@Entity
public class User 
{
	@Id
	private String username;
	
	private String password;
	private String fullname;
	private String email;
	private String notelp;
	private String alamat;
	private String role;
	
	public User()
	{}

	public User(String username, String password, String fullName, String email, String noTelp, String alamat, String role) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullName;
		this.email = email;
		this.notelp = noTelp;
		this.alamat = alamat;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getNotelp() {
		return notelp;
	}

	public void setNotelp(String notelp) {
		this.notelp = notelp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
}
