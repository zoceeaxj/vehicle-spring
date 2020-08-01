package com.rab3tech.controller;

import java.io.IOException;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3tech.controller.dto.ProfileDTO;
import com.rab3tech.service.ProfileService;

@Controller //@Repository , @Service ,@Component
public class AuthController {
	
	@Autowired
	private ProfileService profileService;
	
	
	@PostMapping("/fpassword")
	public String forgotPasswordPost(@RequestParam("usernameEmail") String usernameEmail,Model model) {
		String password=profileService.findPasswordByUsernameOrEmail(usernameEmail);
		if(password.length()==0){
			model.addAttribute("message", "I am sorry , your username and email are not correct!");
		}else{
			model.addAttribute("message", "Hello , your password is = "+password);	
		}
		return "forgotPassword";
	}
	
	@GetMapping("/fpassword")
	public String forgotPassword() {
		return "forgotPassword";
	}
	
		
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) {
		// This code invalidate the session
		if (session != null)
			session.invalidate();

		model.addAttribute("hmmmm", "You have logged out successfully!!");
		return "loginpage";
	}
	
	@GetMapping({"/","/auth"}) //if the URL got / or auth it will show the login page
		public String showLoginPage() {
		
		return "loginpage";
			
		}
	
	
	//spring mvc says ->do not use HttpServletRequest
	//JUNIT - test cases
	@PostMapping("/auth")
	public String validateUser(@RequestParam("username") String username,@RequestParam("password") String password,
			HttpSession session,Model model) {
		ProfileDTO profileDTO=profileService.authUser(username, password);
		if(profileDTO!=null) {
		   //page->request-session-application	
		   //HttpSession session=req.getSession(true); 	
		   session.setAttribute("userData", profileDTO);
		   //adding profileDTO object inside request scope with namemagic
		   //req.setAttribute("magic", profileDTO);
		  return "dashboard";
	  }else {  //user is not there
		  model.addAttribute("hmmmm", "Sorry , username and password are not correct");
		  return "loginpage";
	  }
	}

@GetMapping("/load/image")
public void findPhotoByUsername(@RequestParam String username, HttpServletResponse response) throws IOException {
	
	//Dont want to go the view
	response.setContentType("image/jpg");
	
	byte[] photo = profileService.findPhotoByUsername(username);
	//outputStream - this pointing to body of the response
			ServletOutputStream outputStream=response.getOutputStream();
			if(photo!=null && photo.length>0) {
				//
				outputStream.write(photo);
				outputStream.flush();
			}
			outputStream.close();
	
}	
}