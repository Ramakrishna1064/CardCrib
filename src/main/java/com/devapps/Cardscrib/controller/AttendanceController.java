package com.devapps.Cardscrib.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.devapps.Cardscrib.dto.AttendanceDTO;
import com.devapps.Cardscrib.model.Attendance;
import com.devapps.Cardscrib.model.Response;
import com.devapps.Cardscrib.utils.Conversions;


@RestController
public class AttendanceController {

	@Autowired
	AttendanceDTO attendanceDTO;

	/* Login for user */
	@RequestMapping(value = "/punchAttendance", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	Response userLogin(@RequestBody Attendance attendanceReq) {

		/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDate today = LocalDate.now();
		System.out.println(formatter.format(today));*/
		
		/*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		 
		Date currentDate = new Date();
		 
		System.out.println(formatter.format(currentDate));
		
		attendanceReq.setPunchInTime(formatter.format(currentDate));*/
		
		System.out.println("body : " + Conversions.ObjectToJson(attendanceReq));
		attendanceDTO.save(attendanceReq);
		Response response = new Response();
		response.setStatus(true);
		response.setMessage("Attendance punched successfully");
		return response;
	}

}

