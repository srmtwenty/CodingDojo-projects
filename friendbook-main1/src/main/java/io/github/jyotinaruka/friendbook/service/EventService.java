package io.github.jyotinaruka.friendbook.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.jyotinaruka.friendbook.model.Event;
import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.repositories.EventRepository;

@Service
public class EventService {
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public List<Event> allEvents(){
		return eventRepository.findAll();
	}
	
	public Event submitEvent(Event e) {
		return eventRepository.save(e);
	}
	
	public Event findEvent(Long id) {
		Optional<Event> event = eventRepository.findById(id);
		
		if(event.isPresent()) {
			return event.get();
		}
		else {
			return null;
		}
	}
	
	public Event updateEvent(Long id, String eventname, Date date, String location, User host) {
		Event e = this.findEvent(id);
		e.setEventname(eventname);
		e.setDate(date);
		e.setLocation(location);
		e.setHost(host);
//		e.setAttendees(attendees);
		
		return eventRepository.save(e);
	}
	
	public Event updateEvent(Event e) {
		return eventRepository.save(e);
	}
	
	public Event addAttendee(Event e) {
		return eventRepository.save(e);
	}
	
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}
}
