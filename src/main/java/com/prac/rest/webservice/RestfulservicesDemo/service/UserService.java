package com.prac.rest.webservice.restfulservicesdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prac.rest.webservice.restfulservicesdemo.beans.SocailMediaUser;
import com.prac.rest.webservice.restfulservicesdemo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;

	public List<SocailMediaUser> fetchAll() {
		return userRepository.fetchAll();
	}
	
	public SocailMediaUser fetchAUser(Integer id) {
		return userRepository.fetchById(id);
	}
	
	public SocailMediaUser save(SocailMediaUser socailMediaUser) {
		return userRepository.saveUser(socailMediaUser);
	}
	
	public void deleteAUser(Integer id) {
		userRepository.deleteUser(id);
	}
}
