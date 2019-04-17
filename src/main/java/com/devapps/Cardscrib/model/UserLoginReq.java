package com.devapps.Cardscrib.model;

public class UserLoginReq {

	private String email;
	private String name;
	private String password;
	private String loggedInSource;
	private String socialLoginId;
	private String imei;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getLoggedInSource() {
		return loggedInSource;
	}
	public void setLoggedInSource(String loggedInSource) {
		this.loggedInSource = loggedInSource;
	}
	public String getSocialLoginId() {
		return socialLoginId;
	}
	public void setSocialLoginId(String socialLoginId) {
		this.socialLoginId = socialLoginId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	
}
