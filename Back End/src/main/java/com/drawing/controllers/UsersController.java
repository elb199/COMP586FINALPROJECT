package com.drawing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.drawing.dao.UsersDao;

import com.drawing.models.Users;
import com.drawing.security.DecoderService;

@CrossOrigin()
@RestController
public class UsersController 
{
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DecoderService ds;
	
	
	@RequestMapping(value = "/getID", method = RequestMethod.GET)
	public ResponseEntity<String> getID(@RequestHeader(value="Authorization") String auth)
	{
		String id = ds.getID(auth);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<Users> get(@RequestHeader(value="Authorization") String auth, Users user)
	{
		String id = ds.getID(auth);
		user = usersDao.findByUid(id);
		user = checkForNewUser(id, user);
		usersDao.save(user);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
    public ResponseEntity<Users> post(@RequestHeader(value="Authorization") String auth ,@RequestBody Username username)
    {		
		String id = ds.getID(auth);	
		Users user = usersDao.findByUid(id);
		user = setUsername(checkForNewUser(id, user), username.getUsername());
		usersDao.save(user);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
    }


	private Users setUsername(Users user, String username) 
	{
		if(usersDao.findByUser(username) == null)
		{
			System.out.println("This username is avaliable");
			user.setUser(username);
		}
		else
		{
			System.out.println("This username is taken, try again.");
		}
		return user;
	}
	
	
	private Users checkForNewUser(String id, Users user) 
	{
		if(user == null)
		{
			user = new Users(id, "default");
		}
		return user;
	}
}