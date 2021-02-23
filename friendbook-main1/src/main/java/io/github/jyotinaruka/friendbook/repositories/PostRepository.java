package io.github.jyotinaruka.friendbook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.jyotinaruka.friendbook.model.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

	List<Post> findAllByOrderByCreatedAtDesc();

}
