package com.rab3tech.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl implements ProfileDao {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("pdataSource")
	 * 
	 * private DataSource datasource;
	 * 
	 * JdbcTemplate jdbcTemplate = null;
	 * 
	 * @PostConstruct // This annotation ensures this method will be called for sure
	 * only once after // all the beans creation public void magicMethod() { //
	 * jdbcTemplate = because of line spring jdbcTemplate can talk to database
	 * jdbcTemplate = new JdbcTemplate(datasource); }
	 */
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;

	@Override
	public String updateSignup(ProfileDTO profileDTO) {
		String sql = "update user_login_tbl set name=?,email=?,qualification=?,mobile=?,photo=?,gender=? where username=?";

		Object data[] = { profileDTO.getName(), profileDTO.getEmail(), profileDTO.getQualification(),
				profileDTO.getMobile(), profileDTO.getPhoto(), profileDTO.getGender(), profileDTO.getUsername() };
		jdbcTemplate.update(sql, data);
		return "success";
	}

	@Override
	public String createSignup(ProfileDTO profileDTO) {
		String sql = "insert into  user_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createdate) values(?,?,?,?,?,?,?,?,?)";
		Object data[] = { profileDTO.getUsername(), profileDTO.getPassword(), profileDTO.getName(),
				profileDTO.getEmail(), profileDTO.getQualification(), profileDTO.getMobile(), profileDTO.getPhoto(),
				profileDTO.getGender(), new Timestamp(new Date().getTime()) };

		jdbcTemplate.update(sql, data);
		return "success";
	}

	@Override
	public List<ProfileDTO> sortProfiles(String sort) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl order by email "
				+ sort;
		List<ProfileDTO> profileDTOs = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ProfileDTO.class));

		return profileDTOs;
	}

	@Override
	public List<String> findAllQualification() {
		String sql = "select distinct qualification from user_login_tbl";
		List<String> qualifications = jdbcTemplate.queryForList(sql, String.class);
		return qualifications;
	}

	@Override
	public List<ProfileDTO> filterProfiles(String filterText) {
		// I need to fetch whole profiles data from database
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl  where qualification = ?";
		Object[] data = { filterText };
		List<ProfileDTO> profileDTOs = jdbcTemplate.query(sql, data, new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> searchProfiles(String search) {
		// I need to fetch whole profiles data from database
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl  where name like '%"
				+ search + "%'  or  qualification like '%" + search + "%'";
		List<ProfileDTO> profileDTOs = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> findAll() {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl";
		List<ProfileDTO> profileDTOs = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}

	@Override
	public ProfileDTO findByEmail(String pemail) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where email=?";
		Object[] data = { pemail };
		ProfileDTO profileDTO = jdbcTemplate.queryForObject(sql, data, new BeanPropertyRowMapper<>(ProfileDTO.class));
		return profileDTO;
		/*
		 * List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,data,new
		 * BeanPropertyRowMapper(ProfileDTO.class)); return profileDTOs.get(0);
		 */
	}

	@Override
	public ProfileDTO findByUsername(String pusername) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=?";
		Object[] data = { pusername };
		ProfileDTO profileDTO = jdbcTemplate.queryForObject(sql, data, new BeanPropertyRowMapper<>(ProfileDTO.class));
		return profileDTO;
	}

	@Override
	public void deleteByUsername(String pusername) {
		String sql = "delete from user_login_tbl where username=?";
		Object[] data = { pusername };
		jdbcTemplate.update(sql, data);
	}

	@Override
	public ProfileDTO authUser(String pusername, String ppassword) {
		ProfileDTO profileDTO = null;
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=? and password=?";
		/*
		 * ResultSet rs = null; try (Connection conn = datasource.getConnection();
		 * PreparedStatement pstmt = conn.prepareStatement(sql);) { pstmt.setString(1,
		 * pusername); pstmt.setString(2, ppassword); rs = pstmt.executeQuery(); // Fire
		 * the query // CTR+SHIFT+O if (rs.next()) { // here user is there String
		 * username = rs.getString(1); String password = rs.getString(2); String name =
		 * rs.getString(3); String email = rs.getString(4); String qualification =
		 * rs.getString(5); String mobile = rs.getString(6); String photo =
		 * rs.getString(7); String gender = rs.getString(8); profileDTO = new
		 * ProfileDTO(username, password, name, email, mobile, gender, photo,
		 * qualification); } } catch (Exception e) { e.printStackTrace(); } finally { if
		 * (rs != null) { try { rs.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 }*/
		
		Object[] data = {pusername, ppassword};
		
		try {
		profileDTO = jdbcTemplate.queryForObject(sql, data, new BeanPropertyRowMapper<>(ProfileDTO.class));
		}catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
		return profileDTO;
	}

	@Override
	public void show() {
		/*
		 * try { Connection conn = datasource.getConnection(); if (conn != null)
		 * System.out.println("Connection is established!!!!!!!!!!!!!!!" + conn);
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); }
		 */

	}

	@Override
	public String findPasswordByUsernameOrEmail(String pusernameEmail) {
		String pass = "";
		String sql = "select password from user_login_tbl where username = ? or email = ?";
		Object[] data = {pusernameEmail, pusernameEmail};
		try {
		pass = jdbcTemplate.queryForObject(sql, data, String.class);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pass;
		
	}


	@Override
	public String icreateSignup(ProfileDTO profileDTO) {
		
		byte[] image = null;
		try {
			image = profileDTO.getFile().getBytes();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		LobHandler lobHandler=new DefaultLobHandler();
	    SqlLobValue sqlLobValue=new SqlLobValue(image,lobHandler);

	    String sql = "insert into iuser_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createdate) values(?,?,?,?,?,?,?,?,?)";
		Object data[]={profileDTO.getUsername(),profileDTO.getPassword(),profileDTO.getName(),profileDTO.getEmail(),
				profileDTO.getQualification(),profileDTO.getMobile(),sqlLobValue,profileDTO.getGender(),new Timestamp(new Date().getTime())};
		int[] dataType=new int[] { Types.VARCHAR, Types.VARCHAR,
	               Types.VARCHAR, Types.VARCHAR,  Types.VARCHAR,Types.VARCHAR,Types.BLOB,Types.VARCHAR,Types.TIMESTAMP};

		//Be careful here order is important
		jdbcTemplate.update(sql,data,dataType);
		
		return "success";
	}
	@Override
	public List <ProfileDTO> findAllWithPhoto(){
		String sql = "select username, password,name,email,qualification,mobile,gender,createdate from iuser_login_tbl";
		List <ProfileDTO> profileDTOs = jdbcTemplate.query(sql,new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}
	
	@Override
	public byte[] findPhotoByUsername(String pusername) {
		String sql = "select photo from iuser_login_tbl where username = '"+pusername+"'";
		byte[] photo = jdbcTemplate.queryForObject(sql, byte[].class);
		return photo;
		
	}
	
	@Override
	public String updatePhoto(ProfileDTO profileDTO) {
		byte[] image=null;
		try {
			image=profileDTO.getFile().getBytes();
			if(image!=null && image.length<5){
				return "fail";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    LobHandler lobHandler=new DefaultLobHandler();
	    SqlLobValue sqlLobValue=new SqlLobValue(image,lobHandler);
		String sql = "update  iuser_login_tbl set photo=? where username=?";
		Object data[]={sqlLobValue,profileDTO.getUsername(),};
		int[] dataType=new int[] {Types.BLOB,Types.VARCHAR};
		//Be careful here order is important
		jdbcTemplate.update(sql,data,dataType);
		return "success";
	}
	
}