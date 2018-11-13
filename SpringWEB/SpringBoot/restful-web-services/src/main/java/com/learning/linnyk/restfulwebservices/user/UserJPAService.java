package com.learning.linnyk.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.linnyk.restfulwebservices.user.model.Post;
import com.learning.linnyk.restfulwebservices.user.model.User;
import com.learning.linnyk.restfulwebservices.user.exception.UserNotFoundException;

@Service
public class UserJPAService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	@Autowired
	public UserJPAService(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findOne(int id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("There is no user with id: " + id));
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public Post save(Post post) {
		return postRepository.save(post);
	}


}
