package com.devapps.Cardscrib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devapps.Cardscrib.dto.CompaniesDTO;
import com.devapps.Cardscrib.model.Companies;
import com.devapps.Cardscrib.model.CompaniesRespnse;
import com.devapps.Cardscrib.model.Response;
import com.devapps.Cardscrib.model.UserCardsResponse;
import com.devapps.Cardscrib.model.UsersCards;


@RestController
public class CompaniesController {

	@Autowired
	CompaniesDTO companiesDTO;

	@RequestMapping(value = "/createCompany", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	Response userLogin(@RequestBody Companies companies) {
		companies.setIsActive(1);
		Response response = new Response();
		try{
		companiesDTO.save(companies);
		response.setStatus(true);
		response.setMessage(companies.getName()+" created successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("email id "+companies.getEmail()+" already exists");
		}
		return response;
	}
	
	@RequestMapping("/allCompanies")
	@ResponseBody
	CompaniesRespnse myCards() {
		List<Companies> companies = companiesDTO.findAll();
		CompaniesRespnse companiesResponse = new CompaniesRespnse();
		companiesResponse.setStatus(true);
		companiesResponse.setCompanies(companies);
 		return companiesResponse;
	}

}

