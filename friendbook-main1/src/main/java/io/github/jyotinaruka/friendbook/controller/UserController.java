package io.github.jyotinaruka.friendbook.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(@ModelAttribute("loginUser") User loginUser,
			@ModelAttribute("registerUser") User registerUser) {
		return "loginReg.jsp";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("registerUser") User user, BindingResult result, Model model,
			HttpSession session) {

		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			result.addError(new FieldError("registerUser", "passwordConfirmation",
					"Password does not match with confirm field."));
		}

		if (result.hasErrors()) {
			model.addAttribute("loginUser", new User());
			return "loginReg.jsp";
		} else {
			User registeredUser = userService.registerUser(user);
			session.setAttribute("user_id", registeredUser.getId());
			return "redirect:/home";
		}
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute("loginUser") User user, Model model, HttpSession session,
			RedirectAttributes rA, BindingResult result) {
		// validation
		if (user.getEmail().length() == 0) {
			result.addError(new FieldError("loginUser", "email", "Enter an email."));
		}
		if (user.getPassword().length() == 0) {
			result.addError(new FieldError("loginUser", "password", "Enter password."));
		}
		if (result.hasErrors()) {
			model.addAttribute("registerUser", new User());
			return "loginReg.jsp";
		}

		if (userService.authenticateUser(user.getEmail(), user.getPassword()) == false) {
			String error = "Invalid Credentials";
			rA.addFlashAttribute("error", error);
			return "redirect:/login";
		} else {
			User loggedInUser = userService.findByEmail(user.getEmail());
			session.setAttribute("user_id", loggedInUser.getId());
			return "redirect:/home";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
