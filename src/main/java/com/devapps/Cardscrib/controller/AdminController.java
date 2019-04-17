package com.devapps.Cardscrib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devapps.Cardscrib.dto.AdminDTO;
import com.devapps.Cardscrib.dto.UsersDTO;
import com.devapps.Cardscrib.model.Admin;
import com.devapps.Cardscrib.model.UserLoginReq;
import com.devapps.Cardscrib.model.UserLoginResponse;
import com.devapps.Cardscrib.model.Users;
import com.devapps.Cardscrib.utils.Conversions;


@RestController
public class AdminController {

	@Autowired
	AdminDTO adminDTO;

	/* Login for user */
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	UserLoginResponse userLogin(@RequestBody Admin adminLoginReq) {

		System.out.println("body : " + Conversions.ObjectToJson(adminLoginReq));
		UserLoginResponse userLoginResponse = new UserLoginResponse();
		try {
			Admin dbUsers = adminDTO.findByEmail(adminLoginReq.getEmail());
			if (dbUsers == null) {
				
						userLoginResponse.setStatus(false);
						userLoginResponse.setMessage("User not available");
					
			} else {
				if(adminLoginReq.getPassword().equalsIgnoreCase(dbUsers.getPassword())){
						userLoginResponse.setStatus(true);
						userLoginResponse.setMessage("Logged in successfully");
						userLoginResponse.setId(dbUsers.getId());
						userLoginResponse.setEmail(dbUsers.getEmail());
					} else {
						userLoginResponse.setStatus(false);
						userLoginResponse.setMessage("wrong credentials");
					}
				} 
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			userLoginResponse.setStatus(false);
			userLoginResponse.setMessage("User not available");
		}
		return userLoginResponse;
	}


}

