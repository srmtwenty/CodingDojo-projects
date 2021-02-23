package io.github.jyotinaruka.friendbook.repositories;

import org.springframework.data.repository.CrudRepository;

import io.github.jyotinaruka.friendbook.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

	void deleteAllByPostId(Long id);

}
