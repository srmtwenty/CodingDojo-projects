package io.github.jyotinaruka.friendbook.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.model.UserProfile;
import io.github.jyotinaruka.friendbook.service.UserService;
import io.github.jyotinaruka.friendbook.util.FileUploadUtil;

@Controller
public class PhotoController {
	
	@Autowired
	private UserService userService; 
	
	@Value("${upload.path}")
    private String uploadDir;

	// Referred:
	// https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
	@PostMapping("/photo/profilepic")
	public String submit(@RequestParam("profilePic") MultipartFile file, Model model, HttpSession session) {
		// check user_id in session, otherwise logout user
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/logout";
		}
		User loginUser = userService.findUserById(userId);
		model.addAttribute("loginUser", loginUser);
		
		
		UserProfile profile = userService.getProfileForUser(userId);
		if(profile == null) {
			profile = new UserProfile();
			profile.setUser(loginUser);
		}
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path path = Paths.get(uploadDir, "userphotos/" + loginUser.getId());
 
        try {
			FileUploadUtil.saveFile(path, fileName, file);
			profile.setPicFileName(fileName);         
			profile = userService.saveProfile(profile);
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/profile?error=Please try again!";
		}
        
		return "redirect:/profile";
	}
	
}
