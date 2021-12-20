package com.drawing.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Bio 
{
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "bio")
	private String bio;
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
}