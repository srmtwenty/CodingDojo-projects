package io.github.jyotinaruka.friendbook.controller;

import io.github.jyotinaruka.friendbook.model.Event;
import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.service.EventService;
import io.github.jyotinaruka.friendbook.service.UserService;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventsController {
  @Autowired
  private UserService userService;

  @Autowired
  private EventService eventService;

  // Scott's code
  @RequestMapping("/events")
  public String events(
    @ModelAttribute("user") User user,
    Model model,
    @ModelAttribute("event") Event event,
    HttpSession session
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	model.addAttribute("loginUser", loginUser);

    List<Event> events = eventService.allEvents();
    model.addAttribute("events", events);

    return "events.jsp";
  }

  @RequestMapping("/events/new")
  public String newEvent(
    @ModelAttribute("event") Event event,
    Model model,
    HttpSession session
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	model.addAttribute("loginUser", loginUser);

    return "newEvent.jsp";
  }

  @RequestMapping(value = "/events", method = RequestMethod.POST)
  public String submitEvent(
    @Valid @ModelAttribute("event") Event event,
    BindingResult result,
    HttpSession session,
    Model model
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	model.addAttribute("loginUser", loginUser);
	
    if (result.hasErrors()) {
      return "newEvent.jsp";
    } else {
      User host = userService.findUserById(
        (Long) session.getAttribute("user_id")
      );
      event.setHost(host);

      eventService.submitEvent(event);
      return "redirect:/events";
    }
  }

  @RequestMapping("/events/{id}")
  public String showEvent(
    @PathVariable("id") Long id,
    Model model,
    HttpSession session
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	model.addAttribute("loginUser", loginUser);
	
    Event event = eventService.findEvent(id);
    model.addAttribute("event", event);

    return "showEvent.jsp";
  }

  @RequestMapping("/events/{id}/edit")
  public String editEvent(
    @PathVariable("id") Long id,
    Model model,
    HttpSession session
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	model.addAttribute("loginUser", loginUser);

    Event event = eventService.findEvent(id);
    model.addAttribute("event", event);

    return "editEvent.jsp";
  }

  @RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
  public String updateEvent(
    @Valid @ModelAttribute("event") Event event,
    BindingResult result,
    HttpSession session,
    Model model,
    @PathVariable("id") Long id
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	model.addAttribute("loginUser", loginUser);
	
    if (result.hasErrors()) {
      model.addAttribute("event", event);
      return "editEvent.jsp";
    } else {
      User host = userService.findUserById(
        (Long) session.getAttribute("user_id")
      );
      event.setHost(host);

      eventService.updateEvent(event);
      return "redirect:/events";
    }
  }

  // Join
  @RequestMapping("/events/{id}/join")
  public String addAttentee(
    @PathVariable("id") Long id,
    @ModelAttribute("event") Event event,
    HttpSession session,
    BindingResult result,
    Model model
  ) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	model.addAttribute("loginUser", loginUser);
	
    Event e = eventService.findEvent(id);
    List<User> attendees = e.getAttendees();
    attendees.add(loginUser);
    e.setAttendees(attendees);
    eventService.updateEvent(e);
    return "redirect:/events";
  }

  @RequestMapping("/events/{id}/delete")
  public String deleteEvent(@PathVariable("id") Long id, HttpSession session) {
	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	
    eventService.deleteEvent(id);
    return "redirect:/events";
  }
  
  @RequestMapping("/events/{id}/cancel")
  public String removeAttentee(@PathVariable("id") Long id, 
      @ModelAttribute("event") Event event, HttpSession session, BindingResult result){

	// check user_id in session, otherwise logout user
	Long userId = (Long) session.getAttribute("user_id");
	if (userId == null) {
		return "redirect:/logout";
	}
	User loginUser = userService.findUserById(userId);
	
      Event e = eventService.findEvent(id);
      List<User> attendees = e.getAttendees();
      attendees.remove(loginUser);
      e.setAttendees(attendees);
      eventService.updateEvent(e);
      return "redirect:/events";
  }
}
