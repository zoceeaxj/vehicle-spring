package com.rab3tech.dao;

import java.util.List;



public interface ProfileDao {

	void show();

	String updateSignup(ProfileDTO profileDTO);

	String createSignup(ProfileDTO profileDTO);

	List<ProfileDTO> sortProfiles(String sort);

	List<String> findAllQualification();

	List<ProfileDTO> filterProfiles(String filterText);

	List<ProfileDTO> searchProfiles(String search);

	List<ProfileDTO> findAll();

	ProfileDTO authUser(String pusername, String ppassword);

	ProfileDTO findByEmail(String pemail);

	ProfileDTO findByUsername(String pusername);

	void deleteByUsername(String pusername);

	String findPasswordByUsernameOrEmail(String usernameEmail);

	String icreateSignup(ProfileDTO profileDTO);

	List<ProfileDTO> findAllWithPhoto();

	byte[] findPhotoByUsername(String pusername);

}