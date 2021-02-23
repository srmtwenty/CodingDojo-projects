package io.github.jyotinaruka.friendbook.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.jyotinaruka.friendbook.model.Comment;
import io.github.jyotinaruka.friendbook.model.Post;
import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.service.UserService;


@Controller
public class MainController {
  @Autowired
  private UserService userService;

  
  @GetMapping("/home")
  public String homePage(
    Model model,
    HttpSession session,
    @ModelAttribute("post") Post post
  ) {
    Long userId = (Long) session.getAttribute("user_id");
    User u = userService.findUserById(userId);
    model.addAttribute("loginUser", u);
    model.addAttribute("allPosts", userService.allPosts());
    model.addAttribute(post);
    model.addAttribute("findAll", userService.findAll());

    return "home.jsp";
  }

  @PostMapping("/home")
  public String home(
    @RequestParam("post") String post,
    Model model,
    HttpSession session,
    Long id
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
    User u = userService.findUserById(userId);

    if(post == null || post.trim().length() == 0) {
    	return "redirect:/home";
    }
    
    Post post1 = new Post();
    post1.setMessage(post);

    post1.setPostedBy(u);
    userService.savePost(post1);

    model.addAttribute("allPosts", userService.allPosts());
    model.addAttribute("allComments", userService.allComments());
    //userService.createComment(comment,((userService.findPost(id))));

    return "redirect:/home";
  }

  @PostMapping("/comment/{id}")
  public String comment(
    @RequestParam("message") String comment,
    Model model,
    HttpSession session,
    @PathVariable("id") Long id
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
    User u = userService.findUserById(userId);

    if(comment == null || comment.trim().length() == 0) {
    	return "redirect:/home";
    }
    
    Comment comment1 = new Comment();
    comment1.setMessage(comment);
    comment1.setPost(userService.findPost(id));
    comment1.setCommentedBy(u);
    userService.saveComment(comment1);

    return "redirect:/home";
  }

  @PostMapping("/like/{id}")
  public String like(Model model, HttpSession session, @PathVariable("id")Long id, @RequestParam(value="button")String button) {
		// check user_id in session, otherwise logout user
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/logout";
		}
	  	
	  	Map<Long, Integer> likes = (Map<Long, Integer>) session.getAttribute("likes");
	  	if(likes == null) {
	  		likes = new HashMap<>();
	  	}
	  	
	  	if(button.equals("like")) {
	  		int likeButton = likes.getOrDefault(id, 0);	
	  		likeButton += 1;
	  		likes.put(id, likeButton);
	  	}
	  	session.setAttribute("likes", likes);
	  	return "redirect:/home";
  }

  
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpSession session) {
		// check user_id in session, otherwise logout user
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/logout";
		}
		userService.deleteAllCommentsByPostId(id);
		userService.deletePost(id);
		return "redirect:/home";
	}

}
