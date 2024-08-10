package com.prac.rest.webservice.RestfulservicesDemo.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Post_Info")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private Integer id;
	@Size(message = "Post Content can't exceed 140 charecters.", max = 140, min = 2)
	@Column(name = "post_content")
	private String content;
	@Column(name = "post_time")
	private Date postingTime;
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.LAZY)
	private User user;
	
	public Post() {}
	
	public Post(Integer id, String content,	Date postingTime) {
		this.id = id;
		this.content = content;
		this.postingTime = postingTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Date postingTime) {
		this.postingTime = postingTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
