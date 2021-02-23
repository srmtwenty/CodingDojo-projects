package io.github.jyotinaruka.friendbook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.jyotinaruka.friendbook.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	List<Event> findAll();
}
