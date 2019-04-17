package com.devapps.Cardscrib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.devapps.Cardscrib.dto.UserCardsDTO;
import com.devapps.Cardscrib.dto.UsersDTO;
import com.devapps.Cardscrib.model.Companies;
import com.devapps.Cardscrib.model.Response;
import com.devapps.Cardscrib.model.UserCardsResponse;
import com.devapps.Cardscrib.model.Users;
import com.devapps.Cardscrib.model.UsersCards;


@RestController
public class UsersCardsController {

	@Autowired
	UserCardsDTO userCardsDto;
	
	@Autowired
	UsersDTO usersDto;

	@RequestMapping("/myCards")
	@ResponseBody
	UserCardsResponse myCards(@RequestParam("userId") int userId) {
		List<UsersCards> cards = userCardsDto.findByUserId(userId);
		UserCardsResponse userCardsResponse = new UserCardsResponse();
		userCardsResponse.setStatus(true);
		userCardsResponse.setUserCadsList(cards);
 		return userCardsResponse;
	}
	
	@RequestMapping(value = "/createCard", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	Response createCard(@RequestBody UsersCards usersCards) {
		Response response = new Response();
		try{
			Users dbUsers = usersDto.findById(usersCards.getUserId());
			if (dbUsers == null) {
				response.setStatus(false);
				response.setMessage("there is some problem while creating, seems there is no user");
			} else {
				userCardsDto.save(usersCards);
				response.setStatus(true);
				response.setMessage(usersCards.getCompanyUserName()+" created successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("there is some problem while creating, seems there is no user");
		}
		return response;
	}
}

