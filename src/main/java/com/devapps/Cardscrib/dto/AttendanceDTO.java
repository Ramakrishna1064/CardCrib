package com.devapps.Cardscrib.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devapps.Cardscrib.model.Attendance;

@Repository
public interface AttendanceDTO extends JpaRepository<Attendance, Integer>{

	
	
	
	
}
