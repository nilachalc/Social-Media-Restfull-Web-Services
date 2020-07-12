package com.prac.rest.webservice.RestfulservicesDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prac.rest.webservice.RestfulservicesDemo.beans.Post;
import com.prac.rest.webservice.RestfulservicesDemo.repository.PostRepository;

@Service
@Transactional
public class PostService {
	@Autowired
	PostRepository postRepository;

	public List<Post> fetchAllPostsOfAUser(Integer userid) {
		return postRepository.fetchAllPostsOfAUser(userid);
	}
	
	public Post savePostForAUser(Integer userId, Post post) {
		return postRepository.savePostForAUser(userId, post);
	}
	
	public List<Post> savePostsForAUser(Integer userId, List<Post> posts) {
		return postRepository.savePostsForAUser(userId, posts);
	}
	
	public Post updatePostForAUser(Integer userId, Post post) {
		return postRepository.updatePostForAUser(userId, post);
	}
	
	public Post deletePostOfAUserById(Integer userd, Integer postId) {
		return postRepository.deletePostOfAUserById(userd, postId);
	}
}
