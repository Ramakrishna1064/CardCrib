package com.devapps.Cardscrib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.devapps.Cardscrib.dto.UsersDTO;
import com.devapps.Cardscrib.model.UserLoginReq;
import com.devapps.Cardscrib.model.UserLoginResponse;
import com.devapps.Cardscrib.model.Users;
import com.devapps.Cardscrib.utils.Conversions;


@RestController
public class UsersController {

	@Autowired
	UsersDTO usersDto;

	/* Login for user */
	@RequestMapping(value = "/signUp", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	UserLoginResponse userSignup(@RequestBody Users users) {
		UserLoginResponse userSignupResponse = new UserLoginResponse();
		try {
			Users dbUsers = usersDto.findByEmail(users.getEmail());
			if (dbUsers == null) {
				Users u = usersDto.save(users);
				
				userSignupResponse.setStatus(true);
				userSignupResponse.setMessage("Account created successfully");
				userSignupResponse.setId(u.getId());
				userSignupResponse.setEmail(u.getEmail());
			} else {
				if(dbUsers.getPassword() == null){
					if(dbUsers.getImei()==null || dbUsers.getImei().equalsIgnoreCase(users.getImei())){
					usersDto.updateNativeLogin(users.getPassword(), users.getLoggedInSource(),
							users.getName(), users.getImei(), users.getMobile(), users.getEmail());
					userSignupResponse.setStatus(true);
					userSignupResponse.setMessage("Account created successfully");
					userSignupResponse.setId(dbUsers.getId());
					userSignupResponse.setEmail(dbUsers.getEmail());
					} else {
						userSignupResponse.setStatus(false);
						userSignupResponse.setMessage("account already registered with different device");
					}
				} else {
					userSignupResponse.setStatus(false);
					userSignupResponse.setMessage("account already exists");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Users u = usersDto.save(users);
			
			userSignupResponse.setStatus(true);
			userSignupResponse.setMessage("Account created successfully");
			userSignupResponse.setId(u.getId());
			userSignupResponse.setEmail(u.getEmail());
		}
		return userSignupResponse;
	}
	
	/* Login for user */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	UserLoginResponse userLogin(@RequestBody UserLoginReq userLoginReq) {

		System.out.println("body : " + Conversions.ObjectToJson(userLoginReq));
		UserLoginResponse userLoginResponse = new UserLoginResponse();
		try {
			Users dbUsers = usersDto.findByEmail(userLoginReq.getEmail());
			if (dbUsers == null) {
				if(userLoginReq.getLoggedInSource().equalsIgnoreCase("NATIVE_LOGIN")){
					userLoginResponse.setStatus(false);
					userLoginResponse.setMessage("User not available");
				} else {
					if(userLoginReq.getSocialLoginId()!=null && userLoginReq.getSocialLoginId().length()>1){
						Users users = new Users();
						users.setEmail(userLoginReq.getEmail());
						users.setName(userLoginReq.getName());
						users.setLoggedInSource(userLoginReq.getLoggedInSource());
						users.setImei(userLoginReq.getImei());
						if(userLoginReq.getLoggedInSource().equalsIgnoreCase("FACEBOOK_LOGIN"))
							users.setFacebookId(userLoginReq.getSocialLoginId());
						else
							users.setGplusId(userLoginReq.getSocialLoginId());
						
						Users u = usersDto.save(users);
						
						userLoginResponse.setStatus(true);
						userLoginResponse.setMessage("Logged in successfully");
						userLoginResponse.setId(u.getId());
						userLoginResponse.setEmail(u.getEmail());
					}else {
						userLoginResponse.setStatus(false);
						userLoginResponse.setMessage("User not available");
					}
				}
			} else {
				/*if(dbUsers.getImei() == null){
					//update imei to db
					usersDto.updatImei(userLoginReq.getImei(), userLoginReq.getEmail());
				} else if(dbUsers.getImei().equalsIgnoreCase(userLoginReq.getImei())){*/
				if(userLoginReq.getLoggedInSource().equalsIgnoreCase("NATIVE_LOGIN")){
					if(dbUsers.getPassword()!=null &&
							dbUsers.getPassword().equals(userLoginReq.getPassword()) &&
							dbUsers.getEmail().equalsIgnoreCase(userLoginReq.getEmail())){
						
						/*userLoginResponse.setStatus(true);
						userLoginResponse.setMessage("Logged in successfully");
						userLoginResponse.setId(dbUsers.getId());
						userLoginResponse.setEmail(dbUsers.getEmail());*/
						userLoginResponse = validateImei(dbUsers,userLoginReq);
					} else {
						userLoginResponse.setStatus(false);
						userLoginResponse.setMessage("wrong credentials");
					}
				} else {
					if(userLoginReq.getLoggedInSource().equalsIgnoreCase("FACEBOOK_LOGIN")){
						if(userLoginReq.getSocialLoginId()!= null
								&&userLoginReq.getSocialLoginId().length() > 0 
								&&dbUsers.getFacebookId() != null
								&&userLoginReq.getSocialLoginId().equalsIgnoreCase(dbUsers.getFacebookId())){
							usersDto.updatLoggedInSource(userLoginReq.getLoggedInSource(), userLoginReq.getEmail());
							/*userLoginResponse.setStatus(true);
							userLoginResponse.setMessage("Logged in successfully");
							userLoginResponse.setId(dbUsers.getId());
							userLoginResponse.setEmail(dbUsers.getEmail());*/
							userLoginResponse = validateImei(dbUsers,userLoginReq);
						} else {
							if(dbUsers.getFacebookId()== null){
								usersDto.updatFacebookLogin(userLoginReq.getSocialLoginId(), 
										userLoginReq.getLoggedInSource(), userLoginReq.getEmail());
								/*userLoginResponse.setStatus(true);
								userLoginResponse.setMessage("Logged in successfully");
								userLoginResponse.setId(dbUsers.getId());
								userLoginResponse.setEmail(dbUsers.getEmail());*/
								userLoginResponse = validateImei(dbUsers,userLoginReq);
							}else {
								userLoginResponse.setStatus(false);
								userLoginResponse.setMessage("User not available");
							}
						}
					} else {
						if(userLoginReq.getSocialLoginId()!=null
								&&userLoginReq.getSocialLoginId().length() > 0 
								&& dbUsers.getGplusId() !=null
								&&userLoginReq.getSocialLoginId().equalsIgnoreCase(dbUsers.getGplusId())){
							usersDto.updatLoggedInSource(userLoginReq.getLoggedInSource(), userLoginReq.getEmail());
							/*userLoginResponse.setStatus(true);
							userLoginResponse.setMessage("Logged in successfully");
							userLoginResponse.setId(dbUsers.getId());
							userLoginResponse.setEmail(dbUsers.getEmail());*/
							userLoginResponse = validateImei(dbUsers,userLoginReq);
						} else {
							if(dbUsers.getGplusId()== null){
								usersDto.updatGoogleLogin(userLoginReq.getSocialLoginId(), 
										userLoginReq.getLoggedInSource(), userLoginReq.getEmail());
								/*userLoginResponse.setStatus(true);
								userLoginResponse.setMessage("Logged in successfully");
								userLoginResponse.setId(dbUsers.getId());
								userLoginResponse.setEmail(dbUsers.getEmail());*/
								userLoginResponse = validateImei(dbUsers,userLoginReq);
							}else {
								userLoginResponse.setStatus(false);
								userLoginResponse.setMessage("User not available");
							}
						}
						
					}
					
					
					
				}
				/*} else {
					userLoginResponse.setStatus(false);
					userLoginResponse.setMessage("You are using different device, please check with your organization");
				}*/
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			userLoginResponse.setStatus(false);
			userLoginResponse.setMessage("User not available");
		}
		return userLoginResponse;
	}
	
	private UserLoginResponse validateImei(Users dbUser, UserLoginReq req){
		UserLoginResponse resp = new UserLoginResponse();
		if(dbUser.getImei() == null || dbUser.getImei().length() == 0){
			resp.setStatus(true);
			resp.setMessage("Logged in successfully");
			resp.setId(dbUser.getId());
			resp.setEmail(dbUser.getEmail());
			//update imei to db
			usersDto.updatImei(req.getImei(), req.getEmail());
		} else if(dbUser.getImei().equalsIgnoreCase(req.getImei())){
			resp.setStatus(true);
			resp.setMessage("Logged in successfully");
			resp.setId(dbUser.getId());
			resp.setEmail(dbUser.getEmail());
		} else {
			resp.setStatus(false);
			resp.setMessage("You are using different device, please check with your organization");
		}
		
		return resp;
	}

/*	
	private void sendPushNotification(String deviceToken){
		try {
			   String androidFcmKey="XXXXXXXXXXXX";
			   String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

			   RestTemplate restTemplate = new RestTemplate();
			   HttpHeaders httpHeaders = new HttpHeaders();
			   httpHeaders.set("Authorization", "key=" + androidFcmKey);
			   httpHeaders.set("Content-Type", "application/json");
			   JSONObject msg = new JSONObject();
			   JSONObject json = new JSONObject();

			   msg.put("title", "Title");
			   msg.put("body", "Message");
			   msg.put("notificationType", "Test");

			   json.put("data", msg);
			   json.put("to", deviceToken);

			   HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(),httpHeaders);
			   String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
			   System.out.println(response);
			} catch (JSONException e) {
			   e.printStackTrace();
			}
	}
*/
}

