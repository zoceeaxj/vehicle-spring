package com.rab3tech.dao;

import java.util.List;

public interface ProfileDao {

	void show();

	String updateSignup(ProfileEntity profileEntity);

	String createSignup(ProfileEntity profileEntity);

	List<ProfileEntity> sortProfiles(String sort);

	List<String> findAllQualification();

	List<ProfileEntity> filterProfiles(String filterText);

	List<ProfileEntity> searchProfiles(String search);

	List<ProfileEntity> findAll();

	ProfileEntity authUser(String pusername, String ppassword);

	ProfileEntity findByEmail(String pemail);

	ProfileEntity findByUsername(String pusername);

	void deleteByUsername(String pusername);

	String findPasswordByUsernameOrEmail(String usernameEmail);

	String icreateSignup(ProfileEntity profileEntity);

	List<ProfileEntity> findAllWithPhoto();

	byte[] findPhotoByUsername(String pusername);

	String updatePhoto(ProfileEntity profileEntity);

}