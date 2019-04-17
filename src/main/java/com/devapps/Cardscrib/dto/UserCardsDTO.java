package com.devapps.Cardscrib.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devapps.Cardscrib.model.UsersCards;


@Repository
public interface UserCardsDTO extends JpaRepository<UsersCards, Integer>{

	List<UsersCards> findByUserId(int id);
	
}
