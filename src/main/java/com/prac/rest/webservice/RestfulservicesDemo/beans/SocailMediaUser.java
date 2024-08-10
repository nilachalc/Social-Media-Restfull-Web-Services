package com.prac.rest.webservice.restfulservicesdemo.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Social_Media_User_Info")
public class SocailMediaUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sm_user_id")
	private Integer id;
	@Size(message = "SocailMediaUser Name can't exceed 50 charecters.", max = 50, min = 2)
	@Column(name = "sm_user_name", nullable = false)
	private String name;
	@Past
	@Column(name = "sm_user_dob", nullable = false)
	private Date dob;
	@JsonIgnore
	@OneToMany(targetEntity = Post.class, mappedBy = "socailMediaUser", fetch = FetchType.EAGER)
	private List<Post> posts = new ArrayList<Post>(); 
	
	public SocailMediaUser() {}
	
	public SocailMediaUser(Integer id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void addPosts(Post post) {
		this.posts.add(post);
	}
}
