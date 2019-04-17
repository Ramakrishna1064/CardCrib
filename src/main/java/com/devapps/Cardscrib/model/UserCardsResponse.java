package com.devapps.Cardscrib.model;

import java.util.List;

public class UserCardsResponse {

	private boolean status;
	private List<UsersCards> userCadsList;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<UsersCards> getUserCadsList() {
		return userCadsList;
	}
	public void setUserCadsList(List<UsersCards> userCadsList) {
		this.userCadsList = userCadsList;
	}
	
	
}
