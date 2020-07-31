package com.rab3tech.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3tech.dao.ProfileDTO;
import com.rab3tech.dao.ProfileDao;
import com.rab3tech.utils.Utils;

@Controller
public class CustomerController {

	@Autowired
	private ProfileDao profileDao;

	@GetMapping("/Profiles")
	public String showProfiles(Model model) {
		// I need to fetch whole profiles data from database
		List<ProfileDTO> profileDTOs = profileDao.findAll();
		// adding profileDTO object inside request scope with name magic
		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "profiles";
	}

	@GetMapping("/loggedusers")
	public String loggedusers(Model model) {
		Set<ProfileDTO> loggedUsers = ProfileDTO.loggedInUser();
		model.addAttribute("profileDTOs", loggedUsers);
		return "loggedusers";
	}

	@GetMapping("/filterProfile")
	public String doFilterProfile(@RequestParam("filterText") String filterText, Model model) {
		List<ProfileDTO> profileDTOs = null;
		if (!"Select".equalsIgnoreCase(filterText)) {
			profileDTOs = profileDao.filterProfiles(filterText);
		} else {
			profileDTOs = profileDao.findAll();
		}
		// adding profileDTO object inside request scope with namemagic
		model.addAttribute("listoptions", profileDao.findAllQualification());
		model.addAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@GetMapping("/deleteProfile")
	public String deleteProfile(@RequestParam("username") String username) {
		// String pusername = req.getParameter("username");
		profileDao.deleteByUsername(username);
		return "profiles";
	}

	@GetMapping("/editProfile")
	public String getEditProfile(@RequestParam("username") String username, Model model) {
		// String pusername = req.getParameter("username");
		ProfileDTO profileDTO = profileDao.findByUsername(username);
		model.addAttribute("profileDTO", profileDTO);
		return "esignup";
	}

	@GetMapping("/searchProfile")
	public String searchProfile(@RequestParam("search") String search, Model model) {
		// String search = req.getParameter("search");
		List<ProfileDTO> profileDTOs = profileDao.searchProfiles(search);

		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "profiles";
	}

	@PostMapping("/usignup")
	public String usignup(@ModelAttribute ProfileDTO profileDTO) {
		/*
		 * String username = req.getParameter("username"); String name =
		 * req.getParameter("name"); String email = req.getParameter("email"); String
		 * qualification = req.getParameter("qualification"); String mobile =
		 * req.getParameter("mobile"); String gender = req.getParameter("gender");
		 * String photo = req.getParameter("photo");
		 */
		// ProfileDTO profileDTO = new ProfileDTO(username, "", name, email, mobile,
		// gender, photo, qualification);
		profileDao.updateSignup(profileDTO);
		return "redirect:/profiles";
		// resp.sendRedirect(req.getContextPath()+"/profiles");
	}

	@GetMapping("/signup")
	protected String showSignup() {
		return "signup";
	}

	// <form action="signup" method="post">
	@PostMapping("/signup")
	public String signupPost(@ModelAttribute ProfileDTO profileDTO, Model model) {

		/*
		 * String name = req.getParameter("name"); String email =
		 * req.getParameter("email"); String qualification =
		 * req.getParameter("qualification"); String mobile =
		 * req.getParameter("mobile"); String gender = req.getParameter("gender");
		 * String photo = req.getParameter("photo");
		 */
		String password = Utils.generateRandomPassword(5);
		// String username = email;

		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getEmail());

		// ProfileDao profileDao=new ProfileDaoImpl();
		// ProfileDTO profileDTO = new ProfileDTO(username, password, name, email,
		// mobile, gender, photo, qualification);
		profileDao.createSignup(profileDTO);
		model.addAttribute("hmmmm", "Hi , " + profileDTO.getName() + " , you have done signup successfully!!!!!!!!!!!");
		return "login";

	}

	@PostMapping("/isignup")
	protected String isignup(@ModelAttribute ProfileDTO profileDTO, Model model) {
		System.out.println("I am here");
		String password = Utils.generateRandomPassword(5);
		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getEmail());
		profileDao.icreateSignup(profileDTO);
		model.addAttribute("hmmmm", "Hi , " + profileDTO.getName() + " , You have signed up succesfully");
		return "loginpage";
	}

	@GetMapping("/isignup")
	protected String showSignupWithImage() {
		return "isignup";
	}

	@GetMapping("/sortProfile")
	public String sortProfile(@RequestParam("sort") String sort, Model model) {
		// I need to fetch whole profiles data from database
		// String sort = req.getParameter("sort");
		List<ProfileDTO> profileDTOs = profileDao.sortProfiles(sort);
		// adding profileDTO object inside request scope with namemagic
		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "profiles";

	}

	@GetMapping("/LogOut")
	public String logout(HttpSession session, Model model) {
		// This code invalidate the session
		// HttpSession session = req.getSession(false);
		session.setAttribute("userData", session);
		if (session != null)
			session.invalidate();
		model.addAttribute("hmmmm", "You have logged out successfully!!");
		return "loginpage";
	}
	
	@GetMapping("/iprofiles")
	public String iprofiles(Model model) {
		// I need to fetch whole profiles data from database
		List<ProfileDTO> profileDTOs = profileDao.findAllWithPhoto();
		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileDao.findAllQualification());
		return "iprofiles";
	}

}
