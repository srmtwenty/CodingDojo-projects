package io.github.jyotinaruka.friendbook.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.model.UserProfile;
import io.github.jyotinaruka.friendbook.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		// check user_id in session, otherwise logout user
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/logout";
		}
		User loginUser = userService.findUserById(userId);
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("user", loginUser);

		return "profilePage.jsp";
	}
	
	@GetMapping("/profile/user/{id}")
	public String profile(@PathVariable Long id, Model model, HttpSession session) {
		// check user_id in session, otherwise logout user
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/logout";
		}
		User loginUser = userService.findUserById(userId);
		model.addAttribute("loginUser", loginUser);
		
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		
		return "profilePage.jsp";
	}

	@GetMapping("/profile/edit")
	public String editProfile(Model model, HttpSession session) {
		// check user_id in session, otherwise logout user
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/logout";
		}
		User loginUser = userService.findUserById(userId);
		model.addAttribute("loginUser", loginUser);
		
		UserProfile profile = loginUser.getProfile();
		if(profile == null) {
			profile = new UserProfile();
		}
		model.addAttribute("editUserProfile", profile);
		return "editProfile.jsp";
	}

	@PostMapping("/profile/edit")
	public String updateProfile(@Valid @ModelAttribute("editUserProfile") UserProfile profile, BindingResult result,
			Model model, HttpSession session) {

		// check user_id in session, otherwise logout user
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/logout";
		}
		User loginUser = userService.findUserById(userId);
		model.addAttribute("loginUser", loginUser);

		if (result.hasErrors()) {
			model.addAttribute("editUserProfile", profile);
			return "editProfile.jsp";
		} else {
			UserProfile p = userService.getProfileForUser(userId);
			p = p == null ? new UserProfile() : p;
			p.setBio(profile.getBio());
			p.setEducation(profile.getEducation());
			p.setCurrentCity(profile.getCurrentCity());
			p.setUser(loginUser);
			userService.saveProfile(p);
			return "redirect:/profile";
		}
	}
}
