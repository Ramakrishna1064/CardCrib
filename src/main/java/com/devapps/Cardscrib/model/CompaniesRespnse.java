package com.devapps.Cardscrib.model;

import java.util.List;

public class CompaniesRespnse {

	private boolean status;
	private List<Companies> companies;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<Companies> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Companies> companies) {
		this.companies = companies;
	}
	
	
}
