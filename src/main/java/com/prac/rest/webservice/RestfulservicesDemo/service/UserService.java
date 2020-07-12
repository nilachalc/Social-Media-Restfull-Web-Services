package com.prac.rest.webservice.RestfulservicesDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prac.rest.webservice.RestfulservicesDemo.beans.User;
import com.prac.rest.webservice.RestfulservicesDemo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;

	public List<User> fetchAll() {
		return userRepository.fetchAll();
	}
	
	public User fetchAUser(Integer id) {
		return userRepository.fetchById(id);
	}
	
	public User save(User user) {
		return userRepository.saveUser(user);
	}
	
	public void deleteAUser(Integer id) {
		userRepository.deleteUser(id);
	}
}
