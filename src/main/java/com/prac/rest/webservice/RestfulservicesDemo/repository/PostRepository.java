package com.prac.rest.webservice.restfulservicesdemo.repository;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.rest.webservice.restfulservicesdemo.beans.Post;
import com.prac.rest.webservice.restfulservicesdemo.beans.SocailMediaUser;
import com.prac.rest.webservice.restfulservicesdemo.service.utility.TrackExecutionTime;

@Repository
public class PostRepository {
	@Autowired
	EntityManager entityManager;
	
	public List<Post> fetchAllPostsOfAUser(Integer userId) {
		return entityManager.createQuery("SELECT P FROM Post P WHERE P.user.id = :id", Post.class).setParameter("id", userId).getResultList();
	}
	
	@TrackExecutionTime
	public Post savePostForAUser(Integer userId, Post post) {
		SocailMediaUser socailMediaUser = entityManager.find(SocailMediaUser.class, userId);
		socailMediaUser.addPosts(post);
		post.setUser(socailMediaUser);
		entityManager.persist(post);
		entityManager.flush();
		return post;
	}
	
	@TrackExecutionTime
	public List<Post> savePostsForAUser(Integer userId, List<Post> posts) {
		SocailMediaUser socailMediaUser = entityManager.find(SocailMediaUser.class, userId);
		for (Post post : posts) {
			socailMediaUser.addPosts(post);
			post.setUser(socailMediaUser);
			entityManager.persist(post);
		}
		
		entityManager.flush();
		return socailMediaUser.getPosts();
	}
	
	@TrackExecutionTime
	public Post updatePostForAUser(Integer userId, Post post) {
		SocailMediaUser socailMediaUser = entityManager.find(SocailMediaUser.class, userId);
		Post mergePost = null;
		for (Post dbpost : socailMediaUser.getPosts()) {
			if (dbpost.getId().equals(post.getId())) {
				post.setUser(socailMediaUser);
				mergePost = entityManager.merge(post);
			}
		}
		return mergePost;
	}
	
	public Post deletePostOfAUserById(Integer userId, Integer postId) {
		SocailMediaUser socailMediaUser = entityManager.find(SocailMediaUser.class, userId);
		Post postToBeDeleted = entityManager.find(Post.class, postId);
		socailMediaUser.getPosts().remove(postToBeDeleted);
		entityManager.remove(postToBeDeleted);
		return postToBeDeleted;
	}
}
