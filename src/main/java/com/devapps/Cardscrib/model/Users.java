package com.devapps.Cardscrib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
//	@Column(name = "name")
	private String name;
	
//	@Column(name = "email")
	private String email;
	
//	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "logged_in_source")
	private String loggedInSource;
	
//	@Column(name = "password")
	private String password;
	
	@Column(name = "facebook_id")
	private String facebookId;
	
	@Column(name = "gplus_id")
	private String gplusId;
	
	private String imei;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoggedInSource() {
		return loggedInSource;
	}

	public void setLoggedInSource(String loggedInSource) {
		this.loggedInSource = loggedInSource;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGplusId() {
		return gplusId;
	}

	public void setGplusId(String gplusId) {
		this.gplusId = gplusId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	
	

}
