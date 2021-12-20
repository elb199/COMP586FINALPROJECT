package com.drawing.models;

import java.util.ArrayList;
import java.util.Collection;



import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class Users
{
	@Id 
   
	private String uid;
	private String user;
	private Bio bio;
	
	@ElementCollection//(fetch = FetchType.EAGER) changes from lazy fetch(default)
	@JoinTable(name = "user_drawings" )
	private Collection<Drawings> listOfDrawings = new ArrayList<Drawings>();
	

	public Users(String uid, String user)
	{
		this.uid = uid;
		this.user = user;
	}
	public Users() {}

	
	
	
    @Column(name = "uid", unique = true)
	public String getUid() 
	{
		return uid;
	}
	public void setUid(String uid) 
	{
		this.uid = uid;
	}

	
	
	
	@Column(name = "user", unique = false)
    public String getUser() 
	{
        return user;
    }
    public void setUser(String user) 
    {
        this.user = user;
    }
    
    
    
    public Bio getBio() {
		return bio;
	}
	public void setBio(Bio bio) {
		this.bio = bio;
	}
	
	
	
	public Collection<Drawings> getListOfDrawings() {
		return listOfDrawings;
	}
	public void setListOfDrawings(Collection<Drawings> listOfDrawings) {
		this.listOfDrawings = listOfDrawings;
	}
	
    
    
}