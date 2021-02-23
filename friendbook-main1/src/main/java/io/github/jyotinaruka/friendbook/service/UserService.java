package io.github.jyotinaruka.friendbook.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jyotinaruka.friendbook.model.Comment;
import io.github.jyotinaruka.friendbook.model.Post;
import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.model.UserProfile;
import io.github.jyotinaruka.friendbook.repositories.CommentRepository;
import io.github.jyotinaruka.friendbook.repositories.PostRepository;
import io.github.jyotinaruka.friendbook.repositories.UserProfileRepository;
import io.github.jyotinaruka.friendbook.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserProfileRepository profileRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;



	// register user and hash their password
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepository.save(user);
	}

	// find user by email
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// find user by id
	public User findUserById(Long id) {
		Optional<User> u = userRepository.findById(id);

		if (u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}

	// authenticate user
	public boolean authenticateUser(String email, String password) {
		// first find the user by email
		User user = userRepository.findByEmail(email);
		// if we can't find it by email, return false
		if (user == null) {
			return false;
		} else {
			// if the passwords match, return true, else, return false
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<Post> allPosts(){
		return postRepository.findAllByOrderByCreatedAtDesc();
	}
	
	public List<Comment> allComments(){
		return (List<Comment>) commentRepository.findAll();
	}
	
	public Post createPost(User setPostedBy, Post post) {
		post.setPostedBy(setPostedBy);
		return postRepository.save(post);
	}
	
	public Post savePost(Post post) {
		return postRepository.save(post);
	}
	
	public Comment createComment(Comment comment, Post post) {
		comment.setPost(post);
		return commentRepository.save(comment);
	}
	
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public void addCommentToPost(Comment comment, Post post) {
		comment.setPost(post);
		commentRepository.save(comment);
	}
	public Post findPost(Long id) {
		Optional<Post> p = postRepository.findById(id);

		if (p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}

	public UserProfile saveProfile(UserProfile profile) {
		return profileRepository.save(profile);
	}
	
	public UserProfile getProfileForUser(Long userId) {
		return profileRepository.findFirstByUserId(userId);
	}

	@Transactional
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

	@Transactional
	public void deleteAllCommentsByPostId(Long postId) {
		commentRepository.deleteAllByPostId(postId);
	}
}