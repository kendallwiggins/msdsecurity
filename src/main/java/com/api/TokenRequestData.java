package com.api;

public class TokenRequestData {
	String name;
	String password;
	String scopes;
	
	public TokenRequestData() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TokenRequestData [username=" + name + ", password=" + password + ", scopes=" + scopes + "]";
	}
	public TokenRequestData(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.scopes = "scope";
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getScopes() {
		return scopes;
	}
	
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}	
	
}
