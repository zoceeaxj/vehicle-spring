package com.rab3tech.listener;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.util.ResourceUtils;

@WebListener
public class DBDataLoader implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("()@(@(@*(contextInitialized@*@*@@*");
		// This is new code to access Spring Root Web Application Context
		// ServletContext servletContext=arg0.getServletContext();
		/// ApplicationContext
		// applicationContext=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		// ProfileDao
		// profileDao=applicationContext.getBean(ProfileDaoImpl.class);
		try {
			File file = ResourceUtils.getFile("classpath:db/script.sql");
			String content = FileUtils.readFileToString(file);
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle_db", "root", "Pass123.");
				boolean execute = false;
				try {
					PreparedStatement preparedStatement = conn.prepareStatement("select username from user_login_tbl");
					ResultSet rs = preparedStatement.executeQuery();
					if (!rs.next()) {
						execute = true;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					execute = true;
				}

				if (execute) {
					conn.setAutoCommit(false);
					ScriptUtils.executeSqlScript(conn, new ByteArrayResource(content.getBytes()));
					System.out.println("~~~~~~~~~~~~~~~~~~~~Script is executed successfully for the database");
					conn.commit();//
				}

			} catch (Exception e) {
				if (conn != null) {
					try {
						conn.rollback();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
