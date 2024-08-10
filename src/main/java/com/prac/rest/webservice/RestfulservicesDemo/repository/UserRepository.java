package com.prac.rest.webservice.restfulservicesdemo.repository;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.rest.webservice.restfulservicesdemo.beans.User;

@Repository
public class UserRepository {
	@Autowired
	EntityManager entityManager;
	
	public User fetchById(Integer id) {
		return entityManager.find(User.class, id);
	}
	
	public List<User> fetchAll() {
		return entityManager.createQuery("SELECT U FROM User U", User.class).getResultList();
	}
	
	public User saveUser(User user) {
		if (user.getId() != null) {
			user = entityManager.merge(user);
		} else {
			entityManager.persist(user);
		}
		entityManager.flush();
		return user;
	}
	
	public void deleteUser(Integer id) {
		User userToBeDeleted = entityManager.find(User.class, id);
		entityManager.remove(userToBeDeleted);
	}
}
