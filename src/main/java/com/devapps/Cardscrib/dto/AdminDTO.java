package com.devapps.Cardscrib.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devapps.Cardscrib.model.Admin;

@Repository
public interface AdminDTO extends JpaRepository<Admin, Integer>{
	Admin findByEmail(String email);
}
