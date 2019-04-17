package com.devapps.Cardscrib.dto;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devapps.Cardscrib.model.Users;

@Repository
public interface UsersDTO extends JpaRepository<Users, Integer>{

	
	/*@Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE hrappdb.admin SET password=?1, passwordchanged=?2 WHERE id=?3")
    int updateChangePasswordForAdmin( String password, int passwordChanged, int id);
	*/
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE cardcrib.users SET facebook_id=?1, logged_in_source=?2 WHERE email=?3")
    int updatFacebookLogin( String facebookId,  String loggedInSource, String email);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE cardcrib.users SET gplus_id=?1, logged_in_source=?2 WHERE email=?3")
    int updatGoogleLogin( String facebookId, String loggedInSource, String email);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE cardcrib.users SET password=?1, logged_in_source=?2, name=?3, imei=?4, mobile=?5 WHERE email=?6")
    int updateNativeLogin( String password, String loggedInSource, String name, String imei, String mobile, String email);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE cardcrib.users SET logged_in_source=?1 WHERE email=?2")
    int updatLoggedInSource( String loggedInSource, String email);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value ="UPDATE cardcrib.users SET imei=?1 WHERE email=?2")
    int updatImei( String imei, String email);
	
	
    Users findByEmail(String email);
    Users findById(int id);
}
