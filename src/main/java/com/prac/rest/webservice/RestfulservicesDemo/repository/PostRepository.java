package com.prac.rest.webservice.RestfulservicesDemo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.rest.webservice.RestfulservicesDemo.beans.Post;
import com.prac.rest.webservice.RestfulservicesDemo.beans.User;

@Repository
public class PostRepository {
	@Autowired
	EntityManager entityManager;
	
	public List<Post> fetchAllPostsOfAUser(Integer userId) {
		return entityManager.createQuery("SELECT P FROM Post P WHERE P.user.id = :id", Post.class).setParameter("id", userId).getResultList();
	}
	
	public Post savePostForAUser(Integer userId, Post post) {
		User user = entityManager.find(User.class, userId);
		user.addPosts(post);
		post.setUser(user);
		entityManager.persist(post);
		entityManager.flush();
		return post;
	}
	
	public List<Post> savePostsForAUser(Integer userId, List<Post> posts) {
		User user = entityManager.find(User.class, userId);
		for (Post post : posts) {
			user.addPosts(post);
			post.setUser(user);
			entityManager.persist(post);
		}
		
		entityManager.flush();
		return user.getPosts();
	}
	
	public Post updatePostForAUser(Integer userId, Post post) {
		User user = entityManager.find(User.class, userId);
		Post mergePost = null;
		for (Post dbpost : user.getPosts()) {
			if (dbpost.getId().equals(post.getId())) {
				post.setUser(user);
				mergePost = entityManager.merge(post);
			}
		}
		return mergePost;
	}
	
	public Post deletePostOfAUserById(Integer userId, Integer postId) {
		User user = entityManager.find(User.class, userId);
		Post postToBeDeleted = entityManager.find(Post.class, postId);
		user.getPosts().remove(postToBeDeleted);
		entityManager.remove(postToBeDeleted);
		return postToBeDeleted;
	}
}
