package com.prac.rest.webservice.restfulservicesdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prac.rest.webservice.restfulservicesdemo.beans.SocailMediaUser;
import com.prac.rest.webservice.restfulservicesdemo.repository.SocialMediaUserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	SocialMediaUserRepository socialMediaUserRepository;

	public List<SocailMediaUser> fetchAll() {
		return socialMediaUserRepository.fetchAll();
	}
	
	public SocailMediaUser fetchAUser(Integer id) {
		return socialMediaUserRepository.fetchById(id);
	}
	
	public SocailMediaUser save(SocailMediaUser socailMediaUser) {
		return socialMediaUserRepository.saveUser(socailMediaUser);
	}
	
	public void deleteAUser(Integer id) {
		socialMediaUserRepository.deleteUser(id);
	}
}
