package com.devapps.Cardscrib.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devapps.Cardscrib.model.Companies;

@Repository
public interface CompaniesDTO extends JpaRepository<Companies, Integer>{
}
