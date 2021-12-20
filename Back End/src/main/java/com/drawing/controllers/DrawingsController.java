package com.drawing.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.drawing.models.Drawings;
import com.drawing.models.Users;
import com.drawing.security.DecoderService;
import com.drawing.services.Gzip;

@CrossOrigin()
@RestController
public class DrawingsController 
{
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DecoderService ds;
	@Autowired
	private Gzip compressionService;

	
	@RequestMapping(value = "/open", method = RequestMethod.GET)
	public String get(@RequestHeader(value="Authorization") String auth,  String test)
	{
		//System.out.println("test is: " + test);
		
		String id = ds.getID(auth);
		Users user = usersDao.findByUid(id);
		user = checkForNewUser(id, user);
		
		List<Drawings> myDrawing = (List<Drawings>) user.getListOfDrawings();

		usersDao.save(user);
		
		System.out.println("\n\n\n\n" + myDrawing.get(0).getFile() + "\n\n\n\n\n\n");
		
		return myDrawing.get(0).getFile();
	}
	
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> post(@RequestHeader(value="Authorization") String auth ,@RequestBody String drawing) throws ClassNotFoundException, DecoderException
    {		

		System.out.println("\n\n\n\n\n\n" + drawing + "\n\n\n\n\n");
		
		
		String id = ds.getID(auth);	
		Users user = usersDao.findByUid(id);
		user = setDrawing(checkForNewUser(id, user), drawing);
		usersDao.save(user);
			
	    
		return new ResponseEntity<String>(drawing, HttpStatus.OK);
    }
	

	private String byteToSerialized(byte[] byteArray) throws IOException 
	{
		
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    new ObjectOutputStream(out).writeObject(byteArray);

	    String serializedString = new String(Hex.encodeHex(out.toByteArray()));
	    
	    System.out.println("\n\n\n\n\n\n");
	    
	    System.out.println(serializedString);

	    System.out.println("\n\n\n\n\n\n");
	    
	    return serializedString;
	    
	   
	}


	public void stringArrayTest(byte[] byteArray) throws IOException, ClassNotFoundException, DecoderException {
	   
	    System.out.println(Arrays.toString(byteArray));

	 
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    new ObjectOutputStream(out).writeObject(byteArray);

	  
	    String yourString = new String(Hex.encodeHex(out.toByteArray()));
	    System.out.println(yourString);

	 
	    ByteArrayInputStream in = new ByteArrayInputStream(Hex.decodeHex(yourString.toCharArray()));
	   
	    System.out.println(Arrays.toString( (byte[]) new ObjectInputStream(in).readObject()) );
	}
	
	
	
	private Users setDrawing(Users user, String file) 
	{
		ArrayList<Drawings> allDrawings = new ArrayList<Drawings>();
		
		//create a dao object
		Drawings drawing = new Drawings();
		
		//set the file for it
		drawing.setFile(file);
		drawing.setDate("today");
		drawing.setDescription(3);
		drawing.setTitle("title");
		
		allDrawings.add(drawing);
		
		user.setListOfDrawings(allDrawings);

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