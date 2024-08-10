package com.prac.rest.webservice.restfulservicesdemo.repository;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.rest.webservice.restfulservicesdemo.beans.SocailMediaUser;

@Repository
public class SocialMediaUserRepository {
	@Autowired
	EntityManager entityManager;
	
	public SocailMediaUser fetchById(Integer id) {
		return entityManager.find(SocailMediaUser.class, id);
	}
	
	public List<SocailMediaUser> fetchAll() {
		return entityManager.createQuery("SELECT SMU FROM SocailMediaUser SMU JOIN FETCH SMU.posts p ORDER BY SMU.id", SocailMediaUser.class).getResultList();
	}
	
	public SocailMediaUser saveUser(SocailMediaUser socailMediaUser) {
		if (socailMediaUser.getId() != null) {
			socailMediaUser = entityManager.merge(socailMediaUser);
		} else {
			entityManager.persist(socailMediaUser);
		}
		entityManager.flush();
		return socailMediaUser;
	}
	
	public void deleteUser(Integer id) {
		SocailMediaUser userToBeDeleted = entityManager.find(SocailMediaUser.class, id);
		entityManager.remove(userToBeDeleted);
	}
}
