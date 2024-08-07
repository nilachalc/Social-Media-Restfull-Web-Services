package com.prac.rest.webservice.RestfulservicesDemo.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="This class tracks all the details of a Post.")
@Entity
@Table(name = "Post_Info")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private Integer id;
	@Size(message = "Post Content can't exceed 140 charecters.", max = 140, min = 2)
	@ApiModelProperty(notes="Content should have atleast 2 characters and at max 140 characters.")
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
