package com.devapps.Cardscrib.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "company_id")
	private Integer companyId;
	
	@Column(name = "company_user_id")
	private String companyUserId;
	
	@Column(name = "punch_in_time")
	private Date punchInTime;
	
	@Column(name = "punch_out_time")
	private String punchOutTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(String companyUserId) {
		this.companyUserId = companyUserId;
	}

	public Date getPunchInTime() {
		return punchInTime;
	}

	public void setPunchInTime(Date punchInTime) {
		this.punchInTime = punchInTime;
	}

	public String getPunchOutTime() {
		return punchOutTime;
	}

	public void setPunchOutTime(String punchOutTime) {
		this.punchOutTime = punchOutTime;
	}
	
	
}
