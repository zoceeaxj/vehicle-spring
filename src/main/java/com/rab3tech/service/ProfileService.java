package com.rab3tech.service;

import java.util.List;

import com.rab3tech.controller.dto.ProfileDTO;

// ProfileService is extra layer. will autowire service layer. i need this in my dao. dao is using profile entity 

public interface ProfileService {
	
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

	String updatePhoto(ProfileDTO profileDTO);


}
