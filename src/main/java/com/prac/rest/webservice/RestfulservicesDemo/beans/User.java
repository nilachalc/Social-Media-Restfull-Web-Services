package com.prac.rest.webservice.RestfulservicesDemo.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="This class tracks all the details of an User.")
@Entity
@Table(name = "User_Info")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	@Size(message = "User Name can't exceed 50 charecters.", max = 50, min = 2)
	@ApiModelProperty(notes="Name should have atleast 2 characters and at max 50 characters.")
	@Column(name = "user_name", nullable = false)
	private String name;
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	@Column(name = "user_dob", nullable = false)
	private Date dob;
	@OneToMany(targetEntity = Post.class, mappedBy = "user")
	private List<Post> posts = new ArrayList<Post>(); 
	
	public User() {}
	
	public User(Integer id, String name, Date dob) {
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
