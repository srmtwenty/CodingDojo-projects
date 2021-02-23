package io.github.jyotinaruka.friendbook.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.jyotinaruka.friendbook.model.UserProfile;


@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

	UserProfile findFirstByUserId(Long userId);
	
}
