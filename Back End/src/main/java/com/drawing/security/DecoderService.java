package com.drawing.security;

import org.springframework.stereotype.Service;

import com.auth1.jwt.JWT;
import com.auth1.jwt.exceptions.JWTDecodeException;
import com.auth1.jwt.interfaces.DecodedJWT;

@Service
public class DecoderService
{
	public String getID(String auth)
	{
		String token = parseToken(auth);
		String id = decodeID(token);
	    System.out.println(id.substring(id.lastIndexOf("|") + 1));
		return id.substring(id.lastIndexOf("|") + 1);
	}
	
	public String parseToken(String auth)
	{
		if (auth != null && auth.startsWith("Bearer ")) 
			return auth.substring("Bearer ".length());
		return null;
	}
	
	public String decodeID(String token)
	{
		try 
		{
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getSubject();
		} catch (JWTDecodeException exception){
			//Invalid token
			return null;
		}
	}
}